package com.gloveboxapp.androidchallenge.ui.account

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
import com.gloveboxapp.androidchallenge.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val accountViewModel by viewModels<AccountViewModel>()
    private lateinit var accountAdapter: AccountAdapter
    private lateinit var accountRecyclerView: RecyclerView

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountAdapter = AccountAdapter(
            policyHolderList = ArrayList()
        )

        if (_binding != null) {
            accountRecyclerView = _binding!!.root.findViewById(R.id.account_rv)
            accountRecyclerView.layoutManager = LinearLayoutManager(this.context)
            accountRecyclerView.adapter = accountAdapter
        }

        // Acquiring the list of policies and updating them
        accountViewModel.store.stateFlow.map {
            it.policies
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { policies ->
            accountAdapter.updatePolicyHolderList(policies)
        }

        accountViewModel.getPolicies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
