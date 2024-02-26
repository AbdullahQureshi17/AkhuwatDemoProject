package com.example.akhuwatdemo.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.callback.DataResponseBack
import com.example.akhuwatdemo.model.Constants
import com.example.akhuwatdemo.ui.adapter.FarmerTypeAdapter


/**
 Created By Abdullah
 */


class CustomDialogFragment (val data : DataResponseBack<ArrayList<String>>, val selectedList : ArrayList<Int>): DialogFragment() {
    private lateinit var farmerAdapter: FarmerTypeAdapter
    private lateinit var btnOk : Button






    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_custom_dialog, container, false)
        btnOk = view.findViewById(R.id.btn_ok)



        btnOk.setOnClickListener {

            data.onSuccess(farmerAdapter.fList)
            dialog?.dismiss()

        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val farmerListt= Constants.getFarmerData()


        farmerAdapter = FarmerTypeAdapter(farmerListt, selectedList)


        val recyclerView:RecyclerView=view.findViewById(R.id.farmer_RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        recyclerView.adapter = farmerAdapter



    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }





}
