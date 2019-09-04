package com.diegoginko.androidcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=1500 // 1.5 segundos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Delay para mostrar el splash

            startActivity(Intent(this,MainActivity::class.java)) //Ejecuto el main

            finish() //Cierro este activity
        }, SPLASH_TIME_OUT)
    }
}
