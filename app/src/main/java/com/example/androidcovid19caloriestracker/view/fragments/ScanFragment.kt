/*
 * *
 *  * Created by dev on 8/20/21, 8:41 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/20/21, 8:41 PM
 *
 */
package com.example.androidcovid19caloriestracker.view.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidcovid19caloriestracker.R
import com.example.androidcovid19caloriestracker.databinding.FragmentScanBinding
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class ScanFragment : Fragment() {

    private lateinit var binding: FragmentScanBinding
    private val requestCodeCameraPermission = 1001
    private lateinit var barcodeDetector:BarcodeDetector
    private lateinit var cameraSource: CameraSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBinding.inflate(inflater )

        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            askForCameraPermission()
        } else {
            enableBarcodeScan()
        }
        return binding.root
    }
    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    private fun enableBarcodeScan(){
        barcodeDetector = BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        if(!barcodeDetector!!.isOperational){
            binding.response.text = "Could not set up the detector"
        }
        cameraSource = CameraSource.Builder(requireContext(), barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true)
            .build()
        binding.surfaceView.holder?.addCallback(object: SurfaceHolder.Callback{
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
                        "Failed, something went wrong. Please try again",
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
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableBarcodeScan()
            } else {
                Toast.makeText(requireContext(), "Berechtigung nicht gegeben", Toast.LENGTH_SHORT).show()
            }
        }
    }

}