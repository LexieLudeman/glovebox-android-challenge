package com.gloveboxapp.androidchallenge.ui.editpolicy

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.gloveboxapp.androidchallenge.databinding.FragmentEditPolicyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


@AndroidEntryPoint
class EditPolicyFragment: Fragment() {

    private val editPolicyViewModel by viewModels<EditPolicyViewModel>()

    private var currentPolicyType: PolicyType = PolicyType()

    private lateinit var carrierName: TextView
    private lateinit var agencyName: TextView
    private lateinit var policyHolderName: TextView
    private lateinit var policyNumber: TextView
    private lateinit var typeSpinner: Spinner

    private var policyTypeMap: HashMap<String, PolicyType> = HashMap()
    private var policyTypeList: List<PolicyType> = emptyList()
    private var policyList: List<Policy> = emptyList()
    private var policyTypeNames: ArrayList<String> = ArrayList()

    private var _binding: FragmentEditPolicyBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditPolicyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (_binding != null) {
            editPolicyViewModel.getTypesFromRepo()
        }

        carrierName = view.findViewById(R.id.carrier_text)
        agencyName = view.findViewById(R.id.agency)
        policyHolderName = view.findViewById(R.id.policy_holder)
        policyNumber = view.findViewById(R.id.policy_number)
        typeSpinner = view.findViewById(R.id.policy_type_spinner)

        var selectedPolicyType: String
        var currentPolicyNumber = ""
        var actionBar = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        val args = arguments?.let { EditPolicyFragmentArgs.fromBundle(it) }

        if (args != null) {
            carrierName.text = args.currentPolicy.carrierID
            agencyName.text = args.currentPolicy.agencyName
            policyNumber.text = args.currentPolicy.policyNumber

            val nameBuilder = StringBuilder()
            nameBuilder.append(args.currentPolicy.primaryHolder.firstName)
            nameBuilder.append(" ")
            if (args.currentPolicy.primaryHolder.middleName != null) {
                nameBuilder.append(args.currentPolicy.primaryHolder.middleName)
                nameBuilder.append(" ")
            }
            nameBuilder.append(args.currentPolicy.primaryHolder.lastName)
            policyHolderName.text = nameBuilder.toString()

            currentPolicyType = args.currentPolicy.type
            selectedPolicyType = currentPolicyType.name.toString()
            currentPolicyNumber = args.currentPolicy.policyNumber
        }

        editPolicyViewModel.store.stateFlow.map{
            it.policyTypes
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { policyTypes ->
            policyTypeList = policyTypes
            fillTypeSpinner()
        }
        editPolicyViewModel.getTypesFromRepo()

        policyList = editPolicyViewModel.store.stateFlow.value.policies


        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    selectedPolicyType = typeSpinner.selectedItem.toString()
                    updatePolicyList(currentPolicyNumber, selectedPolicyType)
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    private fun fillTypeSpinner() {
        for (p in policyTypeList) {
            policyTypeNames.add(p.name.toString())
            policyTypeMap[p.name.toString()] = p
        }
        policyTypeNames.sort()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, policyTypeNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val currentTypeName = policyTypeNames.indexOf(currentPolicyType.name)
        with(typeSpinner) {
            setAdapter(adapter)
            setSelection(currentTypeName, false)
            gravity = Gravity.START
        }
    }

    fun updatePolicyList(policyNumber : String, policyType: String) {
        val newPolicyList = ArrayList<Policy>()
        var currentPolicy: Policy

        for (p in policyList) {
            if (p.policyNumber == policyNumber) {
                currentPolicy = p
                currentPolicy.type = policyTypeMap.getOrDefault(policyType, currentPolicyType)
                newPolicyList.add(p)
            } else newPolicyList.add(p)
        }

        editPolicyViewModel.updateStorePolicies(newPolicyList)
    }
}