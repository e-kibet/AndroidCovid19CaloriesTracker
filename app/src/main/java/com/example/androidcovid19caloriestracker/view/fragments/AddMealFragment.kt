package com.example.androidcovid19caloriestracker.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filterable
import androidx.fragment.app.Fragment
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.FragmentAddMealBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat


class AddMealFragment : Fragment() {

    lateinit var binding: FragmentAddMealBinding

        var DAY_OF_WEEK = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddMealBinding.inflate(inflater)
        val adapter = ArrayAdapter(
            requireContext(), R.layout.dropdown_menu_popup_item, DAY_OF_WEEK
        )
        binding.filledExposedDropdown.setAdapter(adapter)

        binding.selecttime.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(22)
                .setMinute(10)
                .setInputMode(INPUT_MODE_KEYBOARD)
                .setTitleText("Select Meal time")
                .build()
                .apply {
                    addOnPositiveButtonClickListener{
                        binding.selecttime.setText(hour.toString() + minute.toString())
                    }
                    addOnCancelListener {
                        binding.selecttime.setText("")
                    }
                }
            requireActivity().supportFragmentManager.let { it1 -> picker.show(it1, "tag") };
        }
        return binding.root
    }

}