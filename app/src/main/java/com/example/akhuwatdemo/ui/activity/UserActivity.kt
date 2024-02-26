package com.example.akhuwatdemo.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService

import androidx.fragment.app.FragmentTransaction
import com.example.akhuwatdemo.ui.fragment.LivestockFragment
import com.example.akhuwatdemo.ui.fragment.IncomeFragment
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.ui.fragment.AgricultureFragment
import com.example.akhuwatdemo.ui.fragment.BusinessFragment
import com.example.akhuwatdemo.ui.fragment.LocationFragment
import com.example.akhuwatdemo.utils.SharedPref
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class UserActivity : AppCompatActivity() {
    lateinit var tvUsername : TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnLocation : Button
    private lateinit var tvLatitude : TextView
    private lateinit var tvLongitude : TextView
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private val interval: Long = 10000 // 10seconds
    private val fastestInterval: Long = 5000 // 5 seconds
    private lateinit var mLastLocation: Location
    private lateinit var mLocationRequest: LocationRequest
    private val requestPermissionCode = 999



    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        btnLocation = findViewById(R.id.btn_location)
//        tvLatitude = findViewById(R.id.tv_latitude)
//        tvLongitude = findViewById(R.id.tv_longitude)
        tvUsername = findViewById(R.id.tv_username)



        //mLocationRequest = LocationRequest.create()


//        btnLocation.setOnClickListener {
//
//           // getCurrentLocation()
//        }

        val a = SharedPref.getInstance(this)
        val b = a.getUserFromSharedPreferences(this)
        val c = b?.UserName
        if (b != null) {
            tvUsername.text = "Welcome \n$c"
        }

//        sharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
//
//        // on below line we are getting the data from
//        // email and setting it in email variable.
//        username = sharedPreferences.getString(USERNAME_KEY, null)!!
//
//        // on below line we are setting a text to user text view.
//        tvUsername.text = "Welcome \n$username"

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        openMyFragment2()

    }
   // @SuppressLint("SetTextI18n")
//    private fun getCurrentLocation(){
//        if(checkPermissions()){
//            if(isLocationEnabled()){
//
//                if (ActivityCompat.checkSelfPermission(
//                        this,
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                        this,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    requestPermission()
//                    return
//                }
//                fusedLocationProviderClient?.lastLocation?.addOnCompleteListener(this) { task->
//                    val location : Location? = task.result
//                    if(location==null){
//                        Toast.makeText(applicationContext,"Null Received",Toast.LENGTH_SHORT).show()
//                    } else{
//                        Toast.makeText(applicationContext,"Get Success",Toast.LENGTH_SHORT).show()
//                        tvLatitude.text = ""+location.latitude
//                        tvLongitude.text = ""+location.longitude
//                    }
//
//
//                }
//            }
//            else{
//                Toast.makeText(applicationContext,"Turn On Location",Toast.LENGTH_SHORT).show()
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
//
//            }
//        }
//        else{
//            requestPermission()
//
//        }
//    }
//    private fun isLocationEnabled() : Boolean{
//        val locationManager : LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//            LocationManager.NETWORK_PROVIDER
//        )
//
//    }
//
//
//
//    companion object{
//        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
//    }
//
//    private fun checkPermissions() : Boolean{
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED){
//            return true
//        }
//        return false
//    }
//    private fun requestPermission() {
//        ActivityCompat.requestPermissions(this,
//            arrayOf( android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION)
//        , PERMISSION_REQUEST_ACCESS_LOCATION)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if(requestCode == PERMISSION_REQUEST_ACCESS_LOCATION){
//            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(applicationContext,"Granted",Toast.LENGTH_SHORT).show()
//                getCurrentLocation()
//            }
//            else{
//                Toast.makeText(applicationContext,"Denied",Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    private fun getLocation() {
//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
//
//            return
//        } else {
//            val location = fusedLocationProviderClient.lastLocation
//            location.addOnSuccessListener {
//                if(it!=null){
//                    val textLatitude = "Latitude: "+it.latitude.toString()
//                    val textlongitude = "Longitude: "+it.longitude.toString()
//                    tvLatitude.text = textLatitude
//                    tvLongitude.text = textlongitude
//
//                }
//            }
//        }
//


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                openMyFragment2()
            }
            R.id.logoutIcon -> {

               val v = SharedPref.getInstance(this)
                v.clear(this)
                val i = Intent(this@UserActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
            R.id.configuration -> {
                openMyFragment()
            }
        }
        return true
    }
    private fun openMyFragment() {
        val fragment = AgricultureFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun openMyFragment2() {
        val fragment = BusinessFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

//    private fun openMyFragment3() {
//        val fragment = LocationFragment()
//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container_view, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }


}