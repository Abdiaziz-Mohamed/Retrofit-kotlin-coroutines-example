package com.example.retrofit_kotlin_coroutines_example.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCustomers() = apiService.getCustomers()
}