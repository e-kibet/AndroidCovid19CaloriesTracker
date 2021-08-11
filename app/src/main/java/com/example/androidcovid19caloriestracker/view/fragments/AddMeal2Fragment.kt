package com.example.androidcovid19caloriestracker.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.FragmentAddMeal2Binding
import com.example.androidcovid19caloriestracker.factory.AddMealViewModelFactory
import com.example.androidcovid19caloriestracker.network.local.FoodDatabase
import com.example.androidcovid19caloriestracker.viewmodel.AddFoodView2Model

class AddMeal2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        val binding = DataBindingUtil.inflate<FragmentAddMeal2Binding>(inflater, R.layout.fragment_add_meal2, container, false)
        binding.lifecycleOwner = this

        val food = AddMeal2FragmentArgs.fromBundle(requireArguments()).selectedFood
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val viewModelFactory = AddMealViewModelFactory(food, dataSource, application, requireContext())
        val viewModel = ViewModelProviders.of(this,
            viewModelFactory).get(AddFoodView2Model::class.java)

        binding.viewModel = viewModel

        binding.addfoodCurrentTime

        viewModel.navigateToOverview.observe(requireActivity(), Observer {
            if (it) {
                this.findNavController().navigate(AddMeal2FragmentDirections.actionAddMeal2ToHome2Fragment())
            }
        })




        return binding.root
    }

}