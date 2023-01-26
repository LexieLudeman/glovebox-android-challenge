package com.gloveboxapp.androidchallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.databinding.FragmentHomeBinding
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl

class HomeFragment : Fragment() {

/*
To do:
    Load the policies into a recyclerview that is retrieved by the HomeViewModel
*/

    private lateinit var homeAdapter: HomeOuterAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var outerRecyclerView: RecyclerView

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

        homeViewModel =
            HomeViewModel(
                repository = GloveBoxRepositoryImpl(
                    context = requireContext().applicationContext
                )
            )

        homeAdapter = HomeOuterAdapter(
            context = requireContext().applicationContext,
            policiesMap = hashMapOf()
        )

        if (_binding != null) {
            outerRecyclerView = _binding!!.root.findViewById(R.id.home_rv)
            outerRecyclerView.layoutManager = LinearLayoutManager (this.context)
            outerRecyclerView.adapter = homeAdapter
        }

        homeViewModel.policies.observe(viewLifecycleOwner) { policies ->
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
