package com.example.retrofit_kotlin_coroutines_example.data.model

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("avatar")
    val image: String,
    @SerializedName("email")
    val customerEmail: String,
    @SerializedName("id")
    val customerId: String,
    @SerializedName("name")
    val customerName: String
)