package com.example.androidcovid19caloriestracker.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
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
        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.bottomNavigationVisibility.observe(this, Observer { toolVisibility ->
            binding.mainToolbar.visibility = toolVisibility
        })

        navController.addOnDestinationChangedListener{_, destination, _->
            run {
                    when (destination.id) {
                        R.id.splashFragment -> {
                            mainViewModel.hideBottomNav()
                            supportActionBar?.hide()
                            binding.fab.hide()
                        }
                        R.id.homeFragment -> {
                            mainViewModel.showBottomNav()
                            binding.fab.show()
                            supportActionBar?.hide()
                            binding.mainToolbar.title = "Covid19 Calories Tracker"
                        }
                        R.id.addMealFragment ->{
                            mainViewModel.showBottomNav()
                            binding.fab.hide()
                            setSupportActionBar(toolbar)
                            supportActionBar?.show()
                            binding.mainToolbar.title = "Add New Meal"
                        }
                        else -> mainViewModel.showBottomNav()
                    }
            }
        }
        binding.fab.setOnClickListener {
            navController.navigate(R.id.addMealFragment)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}