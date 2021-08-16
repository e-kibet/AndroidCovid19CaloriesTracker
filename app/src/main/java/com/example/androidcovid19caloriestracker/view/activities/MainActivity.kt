package com.example.androidcovid19caloriestracker.view.activities

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.ActivityMainBinding
import com.example.androidcovid19caloriestracker.viewmodel.MainViewModel
import com.example.androidcovid19caloriestracker.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var sharedViewModel: SharedViewModel

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val toolbar = binding.mainToolbar
        toolbar.inflateMenu(R.menu.main_menu)
        setSupportActionBar(toolbar)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        val sdf = SimpleDateFormat("yyyy-M-dd")
        sharedViewModel.setNameData(sdf.format(Date()).toString())
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
                        R.id.home2Fragment -> {
                            mainViewModel.showBottomNav()
                            binding.fab.show()
                            supportActionBar?.setDisplayHomeAsUpEnabled(false)
                            binding.mainToolbar.title = "Calories Tracker"
                        }
                        R.id.searchFragment ->{
                            binding.fab.hide()
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
            showDatePickerDialog()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return  super.onCreateOptionsMenu(menu)
    }

    private fun showDatePickerDialog(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_date_picker, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Select the Date")
        val  mAlertDialog = mBuilder.show()
        val datePicker = mDialogView.findViewById<DatePicker>(R.id.datePicker)
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val newMonth = if(month > 9)  month else "0${month}"
            val newYear = if(year > 9) year else "0${year}"
            val newDay = if(day > 9) day else "0${day}"
            val msg = "You Selected: $newYear-$newMonth-$newDay"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            sharedViewModel.setNameData("$newYear-$newMonth-$newDay")
            mAlertDialog.dismiss()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_day_night_mode -> {
                val mode =
                    if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                        Configuration.UI_MODE_NIGHT_NO
                    ) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    }
                AppCompatDelegate.setDefaultNightMode(mode)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}