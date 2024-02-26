package com.example.akhuwatdemo.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.akhuwatdemo.R
import com.example.akhuwatdemo.api.RetrofitClient
import com.example.akhuwatdemo.callback.ApiResponseBack
import com.example.akhuwatdemo.callback.DataResponseBack
import com.example.akhuwatdemo.model.Result
import com.example.akhuwatdemo.model.ResultX
import com.example.akhuwatdemo.model.ResultXX
import com.example.akhuwatdemo.model.ResultXXX
import com.example.akhuwatdemo.utils.GetDataFromApi
import com.example.akhuwatdemo.utils.SharedPref


//import layout.CustomDialogFragment


/**
 Created by Abdullah
 */

class BusinessFragment : Fragment(R.layout.fragment_business) {

    private lateinit var r : RetrofitClient
    private lateinit var spinnerBusiness : Spinner
    private var getDataFromApi =  GetDataFromApi()
    private lateinit var spinnerRegion : Spinner
    private lateinit var spinnerArea : Spinner
    private lateinit var spinnerBranch : Spinner
    private lateinit var btnOpenLiveStockAppraisal : Button
    private lateinit var btnOpenAgricultureAppraisal : Button
    private lateinit var tvDialog : TextView
    val selectedPosition : ArrayList<Int> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business, container, false)
        spinnerBusiness = view.findViewById(R.id.sp_business)
        spinnerRegion = view.findViewById(R.id.sp_region)
        spinnerArea = view.findViewById(R.id.sp_area)
        spinnerBranch = view.findViewById(R.id.sp_branch)
        btnOpenLiveStockAppraisal = view.findViewById(R.id.btn_openLiveStockAppraisal)
        btnOpenAgricultureAppraisal = view.findViewById(R.id.btn_openAgricultureAppraisal)



        getDataFromApi.getBusiness(requireContext(), object : ApiResponseBack<List<Result>> {
            override fun onSuccess(data: List<Result>) {
                setBusinessSpinner(requireContext(), data)
            }

            override fun onFailure(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })

        btnOpenLiveStockAppraisal.setOnClickListener {
            loadFragment(LivestockFragment())
        }

        btnOpenAgricultureAppraisal.setOnClickListener {
            loadFragment(AgricultureFragment())
        }


        return view

            }

    private fun setBusinessSpinner(context: Context, data: List<Result>) {

        val Businessnames = ArrayList<String>()
        if (data != null) {
            for(i in data){
                Businessnames.add(i.BusinessName)
            }
        }
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, Businessnames)

        spinnerBusiness.adapter = arrayAdapter
        spinnerBusiness.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val b = data[p2].BusinessIDHash
                val n = SharedPref.getInstance(context)
                n.saveBusinessID(b)
               getDataFromApi.getRegion(data[p2].BusinessIDHash,context, object : ApiResponseBack<List<ResultX>>{
                   override fun onSuccess(data: List<ResultX>) {
                       setRegionSpinner(context,data)
                   }

                   override fun onFailure(message: String) {
                       Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                   }

               })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun setRegionSpinner(context: Context, data: List<ResultX>) {
        val regionNames = ArrayList<String>()
        if (data != null) {
            for(i in data){
                regionNames.add(i.RegionName)
            }
        }
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, regionNames)

        spinnerRegion.adapter = arrayAdapter

        spinnerRegion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val r = data[p2].RegionIDHash
                val n = SharedPref.getInstance(context)
                n.saveRegionID(r)
                getDataFromApi.getArea(data[p2].BusinessIDHash,data[p2].RegionIDHash,context,object: ApiResponseBack<List<ResultXX>>{
                    override fun onSuccess(data: List<ResultXX>) {
                        setAreaSpinner(context,data)
                    }

                    override fun onFailure(message: String) {
                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                    }

                })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun setAreaSpinner(context: Context, data: List<ResultXX>) {
        val areaNames = ArrayList<String>()
        if (data != null) {
            for (i in data) {
                areaNames.add(i.AreaName)


            }
        }
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, areaNames)
        spinnerArea.adapter = arrayAdapter

        spinnerArea.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val a = data[p2].AreaIDHash
                val n = SharedPref.getInstance(context)
                n.saveAreaID(a)

                getDataFromApi.getBranch(data[p2].BusinessIDHash,data[p2].AreaIDHash,context,object : ApiResponseBack<List<ResultXXX>>{
                    override fun onSuccess(data: List<ResultXXX>) {
                        setBranchSpinner(context,data)
                    }

                    override fun onFailure(message: String) {
                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                    }

                })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }

    private fun setBranchSpinner(context: Context, data: List<ResultXXX>) {

        val branchNames = ArrayList<String>()
        if (data != null) {
            for (i in data) {
                branchNames.add(i.BranchName)

            }
        }
        val arrayAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, branchNames
        )

        spinnerBranch.adapter = arrayAdapter

        spinnerBranch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val br = data[p2].BranchIDHash
                val n = SharedPref.getInstance(context)
                n.saveBranchID(br)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


    }

    private fun loadFragment(fragment : Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()

    }




}






