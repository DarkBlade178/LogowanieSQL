package com.example.logowaniesql

data class Login(
    val id: Int,
    val fistName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
