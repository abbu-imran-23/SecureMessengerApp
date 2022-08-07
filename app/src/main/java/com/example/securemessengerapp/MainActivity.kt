package com.example.securemessengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var splash : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splash = findViewById(R.id.app_logo)
        Handler().postDelayed({
            val intent = Intent(this,Login::class.java)
            finish()
            startActivity(intent)
        },2010)

    }
}