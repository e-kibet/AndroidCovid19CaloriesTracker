package com.example.androidcovid19caloriestracker.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.FragmentSplashBinding
import com.example.androidcovid19caloriestracker.viewmodel.SplashViewModel

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater)

        splashViewModel = SplashViewModel(requireContext())

        splashViewModel.getSplashTimeout()?.observe(requireActivity(), Observer {
            if(it){
                findNavController().navigate(R.id.home2Fragment, null)
            }
        })

        splashViewModel.setSplashTimeout(true)

        return binding.root
    }
}