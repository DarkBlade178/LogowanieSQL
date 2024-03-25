package com.example.logowaniesql

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.logowaniesql.CreateLoginActivity

class LoginActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_panel)

        val textView = findViewById<TextView>(R.id.registerTextView)

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
        val forgotPassword = getString(R.string.forgotPasswordTextView)
        val registerText = getString(R.string.register_textView)
        val spannableString = SpannableString(registerText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                // Przeniesienie użytkownika do CreateLoginActivity po kliknięciu
                val intent = Intent(this@LoginActivity, CreateLoginActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(this@LoginActivity, R.color.colorPurple)
                ds.color = ContextCompat.getColor(this@LoginActivity,R.color.colorPurple)
                ds.isUnderlineText = true // Opcjonalnie, możesz usunąć podkreślenie
            }
        }
        spannableString.setSpan(clickableSpan, registerText.indexOf("Register"), registerText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance() // Konieczne dla aktywacji klikalnego tekstu



        spannableString.setSpan(clickableSpan,forgotPassword.indexOf("Forgot your password?"), forgotPassword.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )
        textView.movementMethod = LinkMovementMethod.getInstance()

        findViewById<TextView>(R.id.forgotPasswordTextView).setOnClickListener {
            startActivity(Intent(this@LoginActivity, ResetPassword::class.java))
        }

    }
}