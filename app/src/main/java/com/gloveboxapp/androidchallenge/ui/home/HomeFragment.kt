package com.gloveboxapp.androidchallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeOuterAdapter
    private lateinit var outerRecyclerView: RecyclerView

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeAdapter = HomeOuterAdapter(
            context = requireContext().applicationContext,
            policyList = ArrayList()
        )

        if (_binding != null) {
            outerRecyclerView = _binding!!.root.findViewById(R.id.home_rv)
            outerRecyclerView.layoutManager = LinearLayoutManager (this.context)
            outerRecyclerView.adapter = homeAdapter
        }

        homeViewModel.store.stateFlow.map{
            it.policies
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { policies ->
            homeAdapter.updatePolicies(policies = policies)
        }

        homeViewModel.getPoliciesFromRepo()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
