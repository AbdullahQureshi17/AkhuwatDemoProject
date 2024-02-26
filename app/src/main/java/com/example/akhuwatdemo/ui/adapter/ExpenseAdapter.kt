package com.example.akhuwatdemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.model.ExpenseSource

class ExpenseAdapter(private var value: List<ExpenseSource>): RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseIdTextView : TextView = itemView.findViewById(R.id.rvitem_tv_expenseIdType)
        val expenseTextView: TextView = itemView.findViewById(R.id.rvitem_tv_expenseType)
        val expenseAmountTextView: TextView = itemView.findViewById(R.id.rvitem_tv_expenseAmountType)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val value = value[position]

        holder.expenseIdTextView.text = value.userOwnerId.toString()
        holder.expenseTextView.text = value.expense
        holder.expenseAmountTextView.text = value.expenseValue.toString()

    }

    override fun getItemCount(): Int {
        return value.size
    }



}