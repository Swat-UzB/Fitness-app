package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.fitnessapp.databinding.FragmentTryAgainBinding


class TryAgainFragment : Fragment() {
    private var _binding: FragmentTryAgainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTryAgainBinding.inflate(inflater, container, false)
        binding.tryAgainButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_tryAgainFragment_to_exerciseFragment3)
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}