package com.example.androidcovid19caloriestracker.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.adapters.Home2RVAdapter
import com.example.androidcovid19caloriestracker.databinding.FragmentHome2Binding
import com.example.androidcovid19caloriestracker.factory.OverviewViewModelFactory
import com.example.androidcovid19caloriestracker.helpers.PreferenceHelper
import com.example.androidcovid19caloriestracker.network.local.FoodDatabase
import com.example.androidcovid19caloriestracker.viewmodel.Home2ViewModel
import com.example.androidcovid19caloriestracker.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class Home2Fragment : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    private lateinit var viewModel: Home2ViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home2, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })

        binding.lifecycleOwner = this
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val dataS = FoodDatabase.getInstance(requireNotNull(activity).application).foodDatabaseDao
        val viewModelFactory2 = OverviewViewModelFactory(dataS,requireNotNull(activity).application)
        viewModel = ViewModelProvider(this, viewModelFactory2).get(Home2ViewModel::class.java)

        sharedViewModel.getNameData()?.observe(requireActivity(), { item->
            viewModel.setDateSelected(item)
            val sharedPrefs = PreferenceHelper(requireContext())
            sharedPrefs.set(item.toString())
            binding.currentDate.text = item.toString()
        })

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        sharedViewModel.setNameData(sdf.format(Date()).toString())

        binding.btnOverviewToSearch.setOnClickListener {

            if(binding.currentDate.text.toString() != getString(R.string.date_not_found)){
                showListViewSelection(it)
            }else{
                Toast.makeText(requireContext(), "Date not found. Please select date", Toast.LENGTH_SHORT).show()
            }
        }

        val application = requireActivity().application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao
        val viewModelFactory = OverviewViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(Home2ViewModel::class.java)
        binding.viewModel = viewModel
        val adapter = Home2RVAdapter(Home2RVAdapter.OnBtnDeleteListener {
            viewModel.onDeleteChoosedFood(it)
        })
        binding.rvOverview.adapter = adapter

        viewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.isNotEmpty()){
                    binding.emptyList.visibility = View.GONE
                    binding.rvOverview.visibility = View.VISIBLE
                    adapter.data = it
                }else{
                    binding.rvOverview.visibility = View.GONE
                    binding.emptyList.visibility  = View.VISIBLE
                }
            }
        })
        return binding.root
    }
    private fun showListViewSelection(view: View) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose the Method to add Food")
        val animals = arrayOf("AutoComplete Search", "Scan Barcode")
        builder.setItems(animals) { _, which ->
            when (which) {
                0 -> Navigation.createNavigateOnClickListener(R.id.action_home2Fragment_to_searchFragment, null).onClick(view)
                1 -> Navigation.createNavigateOnClickListener(R.id.action_home2Fragment_to_scanFragment, null).onClick(view)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }


}