package com.example.androidcovid19caloriestracker.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidcovid19caloriestracker.view.fragments.DailyMealFragment

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter (fragment) {
    override fun getItemCount(): Int  = 7

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DailyMealFragment()
            1 -> DailyMealFragment()
            2 -> DailyMealFragment()
            3 -> DailyMealFragment()
            4 -> DailyMealFragment()
            5 -> DailyMealFragment()
            6 -> DailyMealFragment()
            else -> DailyMealFragment()
        }
    }

}