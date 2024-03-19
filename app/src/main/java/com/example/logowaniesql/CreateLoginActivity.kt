package com.example.logowaniesql

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable

class CreateLoginActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_login)


        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPassword)
        val passwordEditText = findViewById<EditText>(R.id.password)

        passwordEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (passwordEditText.right - passwordEditText.compoundDrawables[drawableEnd].bounds.width())) {
                    // Kliknięto na drawableRight
                    if (passwordEditText.transformationMethod == PasswordTransformationMethod.getInstance()) {
                        // Jeśli hasło jest ukryte, pokaż je
                        passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.baseline_visibility_24, 0)
                    } else {
                        // Jeśli hasło jest widoczne, ukryj je
                        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.baseline_visibility_off_24, 0)
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        confirmPasswordEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (confirmPasswordEditText.right - confirmPasswordEditText.compoundDrawables[drawableEnd].bounds.width())) {
                    // Kliknięto na drawableRight
                    if (confirmPasswordEditText.transformationMethod == PasswordTransformationMethod.getInstance()) {
                        // Jeśli hasło jest ukryte, pokaż je
                        confirmPasswordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        confirmPasswordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.baseline_visibility_24, 0)
                    } else {
                        // Jeśli hasło jest widoczne, ukryj je
                        confirmPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                        confirmPasswordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.baseline_visibility_off_24, 0)
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
        val loginText = "Already have an account? Login"
        val spannableString = SpannableString(loginText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                // Przeniesienie użytkownika do CreateLoginActivity po kliknięciu
                val intent = Intent(this@CreateLoginActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@CreateLoginActivity,R.color.colorPurple)
                ds.isUnderlineText = true // Opcjonalnie, możesz usunąć podkreślenie
            }
        }
        val textView = findViewById<TextView>(R.id.loginTextView)

        spannableString.setSpan(clickableSpan, loginText.indexOf("Login"), loginText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance() // Konieczne dla aktywacji klikalnego tekstu


    }

}