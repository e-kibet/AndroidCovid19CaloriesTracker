package com.example.androidcovid19caloriestracker.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.adapters.OverviewRVAdapter
import com.example.androidcovid19caloriestracker.databinding.FragmentHome2Binding
import com.example.androidcovid19caloriestracker.factory.OverviewViewModelFactory
import com.example.androidcovid19caloriestracker.network.local.FoodDatabase
import com.example.androidcovid19caloriestracker.viewmodel.OverviewViewModel

class Home2Fragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home2, container, false)
        binding.lifecycleOwner = this

        val application = requireActivity().application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val viewModelFactory = OverviewViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OverviewViewModel::class.java)
        binding.viewModel = viewModel
        val adapter = OverviewRVAdapter(OverviewRVAdapter.OnBtnDeleteListener {
            viewModel.onDeleteChoosedFood(it)
        })

        binding.rvOverview.adapter = adapter

        viewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })



        return binding.root
    }


}