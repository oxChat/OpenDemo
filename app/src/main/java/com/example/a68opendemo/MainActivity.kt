package com.example.a68opendemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAuthLogin).setOnClickListener {
            val intent = Intent(this,AuthLoginActivity::class.java)
            startActivity(intent)
        }
    }
}