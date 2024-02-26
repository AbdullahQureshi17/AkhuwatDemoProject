package com.example.akhuwatdemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.model.FarmerType

class FarmerTypeAdapter(val fList: ArrayList<FarmerType>, val selectedList: ArrayList<Int>) : RecyclerView.Adapter<FarmerTypeAdapter.FarmerViewHolder>() {



    class FarmerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkFarmerType : CheckBox = itemView.findViewById(R.id.cb_Ownerr)
        val farmerTextView: TextView = itemView.findViewById(R.id.tv_Ownerr)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FarmerViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        val value = fList[position]

        holder.farmerTextView.text = value.name

        for(i in selectedList) {
            if (position == i) {
                holder.checkFarmerType.isChecked = true
            }
        }
        holder.checkFarmerType.setOnCheckedChangeListener { buttonView, isChecked ->

            value.check = isChecked

        }


    }

    override fun getItemCount(): Int {
        return fList.size
    }




}