package com.example.androidcovid19caloriestracker.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.ActivityMainBinding
import com.example.androidcovid19caloriestracker.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.bottomNavigationVisibility.observe(this, Observer { toolVisibility ->
            binding.mainToolbar.visibility = toolVisibility
        })

        navController.addOnDestinationChangedListener{_, destination, _->
            run {
                    when (destination.id) {
                        R.id.splashFragment -> mainViewModel.hideBottomNav()
                        R.id.homeFragment -> {
                            mainViewModel.showBottomNav()
                            binding.mainToolbar.title = "Covid19 Calories Tracker"
                        }
                        else -> mainViewModel.showBottomNav()
                    }
            }
        }
        val toolbar = binding.mainToolbar

        setSupportActionBar(toolbar)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}