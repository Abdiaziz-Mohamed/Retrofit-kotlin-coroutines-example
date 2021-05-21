package com.example.retrofit_kotlin_coroutines_example.data.repository

import com.example.retrofit_kotlin_coroutines_example.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getCustomers() = apiHelper.getCustomers()
}