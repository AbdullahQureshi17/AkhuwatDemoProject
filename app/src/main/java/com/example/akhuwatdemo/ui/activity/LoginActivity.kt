package com.example.akhuwatdemo.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.akhuwatdemo.ui.fragment.LoginFragment
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.database.AppDatabase
import com.example.akhuwatdemo.ui.fragment.RegisterFragment

class LoginActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        openMyFragment()

    }

    private fun openMyFragment() {
        val fragment = LoginFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


























}