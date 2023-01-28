package com.gloveboxapp.androidchallenge.ui.editpolicy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.gloveboxapp.androidchallenge.databinding.FragmentEditPolicyBinding
import com.gloveboxapp.androidchallenge.redux.ApplicationState
import com.gloveboxapp.androidchallenge.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditPolicyFragment @Inject constructor(
    val store: Store<ApplicationState>
): Fragment() {

    private val editPolicyViewModel: EditPolicyViewModel by lazy {
        ViewModelProvider(this)[EditPolicyViewModel::class.java]
    }

    private lateinit var typeSpinner: Spinner
    private lateinit var currentPolicyType: PolicyType
    private lateinit var selectedPolicyType: PolicyType

    private var _binding: FragmentEditPolicyBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPolicyBinding.inflate(inflater, container, false)

        if (_binding != null) {
            //TODO change the values on the layout

            //TODO get policyTypes and put into spinner
            editPolicyViewModel.getTypesFromRepo()
        }
        return binding.root
    }

    fun readPoliciesFromStore(){}

    fun readPolicyTypesFromStore(){}

    fun writeChangesToStore(){

    }

}