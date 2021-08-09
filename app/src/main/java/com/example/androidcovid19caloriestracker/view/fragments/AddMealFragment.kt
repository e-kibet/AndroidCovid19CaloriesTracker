package com.example.androidcovid19caloriestracker.view.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.FragmentAddMealBinding
import com.example.androidcovid19caloriestracker.viewmodel.AddMealViewModel
import com.example.androidcovid19caloriestracker.viewmodel.MainViewModel
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat


class AddMealFragment : Fragment() {

    private lateinit var binding: FragmentAddMealBinding

    private lateinit var barcodeDetector:BarcodeDetector

    private lateinit var cameraSource: CameraSource

    private var DAY_OF_WEEK = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    private val requestCodeCameraPermission = 1001

    private lateinit var addMealViewModel:AddMealViewModel

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

        addMealViewModel = ViewModelProvider(requireActivity()).get(AddMealViewModel::class.java)

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
                        binding.selecttime.setText("$hour:$minute")
                    }
                    addOnCancelListener {
                        binding.selecttime.setText("")
                    }
                }
            requireActivity().supportFragmentManager.let { it1 -> picker.show(it1, "tag") };
        }

        binding.continueButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                askForCameraPermission()
            } else {
                showBottomSheetDialog()
            }
        }
        return binding.root
    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun showBottomSheetDialog(){
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.add_food_line_sheet)
        val surfaceView: SurfaceView? = bottomSheetDialog.findViewById(R.id.surface_view)
        val autoSearchView: TextInputLayout? = bottomSheetDialog.findViewById(R.id.autoSearchView)
        barcodeDetector = BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        cameraSource = CameraSource.Builder(requireContext(), barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true)
            .build()
        val switchIcon: Switch? = bottomSheetDialog.findViewById(R.id.switchicon)

        addMealViewModel.autoSearchBarVisibility.observe(requireActivity(), Observer{ searchVisibility ->
            surfaceView?.visibility = searchVisibility
        })
        addMealViewModel.qrScanVisibility.observe(requireActivity(), Observer { qrVisibility ->
            autoSearchView?.visibility = qrVisibility
        })

        switchIcon?.setOnCheckedChangeListener{ _, isChecked->
            run {
                if (isChecked) {
                    addMealViewModel.showAutocompleteSearchBar()
                    addMealViewModel.hideQrScan()
                } else {
                    addMealViewModel.hideAutocompleteSearchBar()
                    addMealViewModel.showQrScan()
                }
            }
        }

        surfaceView?.holder?.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            android.Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    cameraSource.start(holder)
                }catch (exception: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Ups, da ist was schief gelaufen...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }

        })
        bottomSheetDialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showBottomSheetDialog()
            } else {
                Toast.makeText(requireContext(), "Berechtigung nicht gegeben", Toast.LENGTH_SHORT).show()
            }
        }
    }

}