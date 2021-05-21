package com.example.retrofit_kotlin_coroutines_example.ui.main.adapter

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_kotlin_coroutines_example.R
import com.example.retrofit_kotlin_coroutines_example.data.model.Customer
import kotlinx.android.synthetic.main.my_item.view.*
import java.util.Collections.addAll

class MainAdapter(private val customers: ArrayList<Customer>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_item, parent, false))

    override fun getItemCount(): Int = customers.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
    holder.bind(customers[position])
}


class DataViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(customer: Customer) {
        itemView.apply {
            textViewUserName.text = customer.customerName
            textViewUserEmail.text = customer.customerEmail
            Glide.with(imageViewAvatar.context)
                .load(customer.image)
                .into(imageViewAvatar)
        }
    }
}
    fun addUsers(customers: ArrayList<Customer>) {
        this.customers.apply {
            clear()
            addAll(customers)
        }
    }
    }

