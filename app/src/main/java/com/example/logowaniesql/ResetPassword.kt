package com.example.logowaniesql

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResetPassword: AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        findViewById<TextView>(R.id.back).setOnClickListener {
            val intent = Intent(this@ResetPassword, LoginActivity::class.java)
            startActivity(intent)
            true
        }
    }
}