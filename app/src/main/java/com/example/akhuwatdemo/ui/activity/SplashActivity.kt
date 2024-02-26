package com.example.akhuwatdemo.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.ui.fragment.LoginFragment
import com.example.akhuwatdemo.utils.SharedPref

class SplashActivity : AppCompatActivity() {



    private lateinit var tvSplash : TextView
    private lateinit var move : Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tvSplash = findViewById(R.id.tv_splash)

            val sharedPref = SharedPref.getInstance(this)

        val userr = sharedPref.getUserFromSharedPreferences(this)


        val delayMillis: Long = 2000


        move = AnimationUtils.loadAnimation(this, R.anim.move)

        tvSplash.startAnimation(move)

       Handler(Looper.getMainLooper()).postDelayed({

            if(userr == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            }
            else{
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)

            }
            finish()
        }, delayMillis)


    }




}



