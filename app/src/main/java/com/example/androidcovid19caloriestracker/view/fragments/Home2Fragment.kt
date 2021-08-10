package com.example.androidcovid19caloriestracker.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.adapters.OverviewRVAdapter
import com.example.androidcovid19caloriestracker.databinding.FragmentHome2Binding
import com.example.androidcovid19caloriestracker.factory.OverviewViewModelFactory
import com.example.androidcovid19caloriestracker.network.local.FoodDatabase
import com.example.androidcovid19caloriestracker.viewmodel.OverviewViewModel
import com.example.androidcovid19caloriestracker.viewmodel.SharedViewModel

class Home2Fragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home2, container, false)
        binding.lifecycleOwner = this


        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.getNameData()?.observe(requireActivity(), { item->
            Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
            binding.currentDate.text = item.toString()
        })

        binding.btnOverviewToSearch.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_SearchFragment)
        }

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