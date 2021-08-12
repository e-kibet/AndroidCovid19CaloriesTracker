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
import com.example.androidcovid19caloriestracker.adapters.SearchItemAdapter
import com.example.androidcovid19caloriestracker.databinding.FragmentSearchBinding
import com.example.androidcovid19caloriestracker.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = SearchItemAdapter(SearchItemAdapter.OnClickListener {
            viewModel.displayAddFood(it)
        })
        binding.searchRecyclerview.adapter = adapter

        viewModel.searchListFood.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        viewModel.navigateToSelectedFood.observe(requireActivity(), Observer {
            if (it != null) {
                this.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToAddFood2Fragment(it))
                viewModel.displayAddFoodIsComplete()
            }
        })

        viewModel.searchInProgress.observe(requireActivity(), Observer {
            if (it == false) {
                binding.searchProgressbar.visibility = View.INVISIBLE
            } else {
                binding.searchProgressbar.visibility = View.VISIBLE
            }
        })

        viewModel.showFoodNotFound.observe(requireActivity(), Observer {
            if (it == false) {
                binding.searchRecyclerview.visibility = View.VISIBLE
                binding.tvNotFound.visibility = View.INVISIBLE
            } else {
                binding.searchRecyclerview.visibility = View.INVISIBLE
                binding.tvNotFound.visibility = View.VISIBLE
            }
        })


        return binding.root
    }


}