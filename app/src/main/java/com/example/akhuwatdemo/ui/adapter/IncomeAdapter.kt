package com.example.akhuwatdemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.model.IncomeSource

class IncomeAdapter(private var values: List<IncomeSource>): RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    class IncomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incomeIdTextView : TextView = itemView.findViewById(R.id.rvitem_tv_incomeIdType)
        val incomeTextView: TextView = itemView.findViewById(R.id.rvitem_tv_IncomeType)
        val incomeAmountTextView: TextView = itemView.findViewById(R.id.rvitem_tv_IncomeAmountType)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.income_item, parent, false)
        return IncomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val value = values[position]

        holder.incomeIdTextView.text = value.userOwnerId.toString()
        holder.incomeTextView.text = value.income
        holder.incomeAmountTextView.text = value.incomeValue.toString()

    }

    override fun getItemCount(): Int {
        return values.size
    }



}