package com.example.retrofit_kotlin_coroutines_example.data.api

import com.example.retrofit_kotlin_coroutines_example.data.model.Customer
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getCustomers(): List<Customer>

}