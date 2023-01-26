package com.gloveboxapp.androidchallenge.ui.carriers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gloveboxapp.androidchallenge.databinding.FragmentCarriersBinding

class CarriersFragment : Fragment() {
/*
To do:
    Group the records in the policies array by carrierID and render one list for each carrier
    with each list containing the policies associated with that carrier
*/

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
