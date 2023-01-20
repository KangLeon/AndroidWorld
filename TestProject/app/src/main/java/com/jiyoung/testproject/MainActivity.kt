package com.jiyoung.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindUI()
    }

    fun bindUI() {
        val button1: Button = findViewById(R.id.button0)
        button1.setOnClickListener {
            Toast.makeText(this, "You clciked Button 0", Toast.LENGTH_SHORT).show()
        }
    }
}