package com.example.retrofit_kotlin_coroutines_example.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_kotlin_coroutines_example.R
import com.example.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.example.retrofit_kotlin_coroutines_example.data.api.RetrofitBuilder
import com.example.retrofit_kotlin_coroutines_example.data.model.Customer
import com.example.retrofit_kotlin_coroutines_example.ui.main.adapter.MainAdapter
import com.example.retrofit_kotlin_coroutines_example.ui.main.viewmodel.MainViewModel
import com.example.retrofit_kotlin_coroutines_example.ui.main.viewmodel.ViewModelFactory
import com.example.retrofit_kotlin_coroutines_example.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getCustomers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(customers: List<Customer>) {
        adapter.apply {
            addUsers(customers as ArrayList<Customer>)
            notifyDataSetChanged()
        }

    }
    }