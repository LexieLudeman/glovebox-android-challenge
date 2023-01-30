package com.gloveboxapp.androidchallenge.ui.carriers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.databinding.FragmentCarriersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class CarriersFragment : Fragment() {

    private val carriersViewModel by viewModels<CarriersViewModel>()
    private lateinit var carriersAdapter: CarriersAdapter
    private lateinit var carriersRecyclerView: RecyclerView

    private var _binding: FragmentCarriersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarriersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carriersAdapter = CarriersAdapter(
            carrierList = ArrayList()
        )

        if (_binding != null) {
            carriersRecyclerView = _binding!!.root.findViewById(R.id.carriers_rv)
            carriersRecyclerView.layoutManager = LinearLayoutManager(this.context)
            carriersRecyclerView.adapter = carriersAdapter

        }

        // Acquiring the list of policies and updating them

        carriersViewModel.store.stateFlow.map {
            it.policies
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { policies ->
            carriersAdapter.updateCarriersList(policies)
        }

        carriersViewModel.getPolicies()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
