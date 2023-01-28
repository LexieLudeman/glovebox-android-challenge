package com.gloveboxapp.androidchallenge.ui.editpolicy

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.gloveboxapp.androidchallenge.databinding.FragmentEditPolicyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class EditPolicyFragment: Fragment() {

    private val editPolicyViewModel: EditPolicyViewModel by lazy {
        ViewModelProvider(this)[EditPolicyViewModel::class.java]
    }

    private lateinit var typeSpinner: Spinner
    private var currentPolicyType: PolicyType = PolicyType()
    private var selectedPolicyType: PolicyType = PolicyType()

    private lateinit var carrierName: TextView
    private lateinit var agencyName: TextView
    private lateinit var policyHolderName: TextView
    private lateinit var policyNumber: TextView

    private var policyTypeMap: HashMap<String, PolicyType> = HashMap()
    private var policyTypeList: List<PolicyType> = emptyList()
    private var policyTypeNames: ArrayList<String> = ArrayList()

    private var _binding: FragmentEditPolicyBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            selectedPolicyType = currentPolicyType

        }

        editPolicyViewModel.store.stateFlow.map{
            it.policyTypes
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { policyTypes ->
            policyTypeList = policyTypes
            fillTypeSpinner()
        }
        editPolicyViewModel.getTypesFromRepo()
        Log.d("Data", policyTypeList.toString())

    }

    private fun fillTypeSpinner() {
        for (p in policyTypeList) {
            policyTypeNames.add(p.name.toString())
            policyTypeMap[p.name.toString()] = p
        }
        policyTypeNames.sort()

        var adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, policyTypeNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var currentTypeName = policyTypeNames.indexOf(currentPolicyType.name)
        with(typeSpinner) {
            setAdapter(adapter)
            setSelection(currentTypeName, false)
            gravity = Gravity.START
        }
    }


    fun readPoliciesFromStore(){}

    fun readPolicyTypesFromStore(){}

    fun writeChangesToStore(){

    }

}