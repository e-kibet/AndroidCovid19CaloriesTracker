package com.example.androidcovid19caloriestracker.view.fragments

import android.content.Context
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
import com.example.androidcovid19caloriestracker.utils.getCurrentDayString
import com.example.androidcovid19caloriestracker.view.activities.MainActivity
import com.example.androidcovid19caloriestracker.viewmodel.OverviewViewModel

class Home2Fragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var listenerCurrent: OnOverviewCurrent

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
    override fun onStart() {
        super.onStart()
        val activity = activity as MainActivity
        val selectedDate = activity.selectedDate
        if (selectedDate != null) {
            viewModel.setDateSelected(selectedDate)
            if (selectedDate != getCurrentDayString()) {
                // Hide search btn
                binding.btnOverviewToSearch.visibility = View.VISIBLE
            } else {
                binding.btnOverviewToSearch.visibility = View.VISIBLE
            }

        }

        listenerCurrent.onOverviewCurrent(true)
    }

    interface OnOverviewCurrent {
        fun onOverviewCurrent(isCurrent: Boolean)
    }

    override fun onStop() {
        super.onStop()
        listenerCurrent.onOverviewCurrent(false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnOverviewCurrent) {
            listenerCurrent = context
        } else {
            throw ClassCastException("$context must implement OnOverviewCurrent.")
        }
    }


}