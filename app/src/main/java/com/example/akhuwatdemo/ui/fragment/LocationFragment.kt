package com.example.akhuwatdemo.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

import com.example.akhuwatdemo.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

/**
 Created by Abdullah
 */
class LocationFragment : Fragment(R.layout.fragment_location) {
    private lateinit var btnLocation : Button
    private lateinit var tvLatitude : TextView
    private lateinit var tvLongitude : TextView
    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        btnLocation = view.findViewById(R.id.btn_location)
        tvLatitude = view.findViewById(R.id.tv_latitude)
        tvLongitude = view.findViewById(R.id.tv_longitude)
        //val locationPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions)

        btnLocation.setOnClickListener {


            getLocation()
        }




        return view
    }
    private fun getLocation(){
        if(ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        } else {
            val location = fusedLocationProviderClient.lastLocation
            location.addOnSuccessListener {
                if(it!=null){
                    val textLatitude = "Latitude: "+it.latitude.toString()
                    val textlongitude = "Longitude: "+it.longitude.toString()
                    tvLatitude.text = textLatitude
                    tvLongitude.text = textlongitude

                }
            }
        }

    }


    }
