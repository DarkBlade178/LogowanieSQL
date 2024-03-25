package com.example.logowaniesql

import android.provider.ContactsContract.CommonDataKinds.Email

data class Login(val id: Int, val fistName: String, val lastName: String, val email: String, val password: String, val confirmPassword: String )
