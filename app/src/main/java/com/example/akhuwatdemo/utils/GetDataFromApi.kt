package com.example.akhuwatdemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.example.akhuwatdemo.api.RetrofitClient
import com.example.akhuwatdemo.callback.ApiResponseBack
import com.example.akhuwatdemo.model.ApiAgriAppraisalResponse
import com.example.akhuwatdemo.model.ApiAgriAppraisalTypeResponse
import com.example.akhuwatdemo.model.ApiAreaResponse
import com.example.akhuwatdemo.model.ApiAreaTypeResponse
import com.example.akhuwatdemo.model.ApiBranchResponse
import com.example.akhuwatdemo.model.ApiBusinessPlaceResponse
import com.example.akhuwatdemo.model.ApiBusinesssResponse
import com.example.akhuwatdemo.model.ApiFarmSeasonTypeResponse
import com.example.akhuwatdemo.model.ApiIrrigationSourceTypeResponse
import com.example.akhuwatdemo.model.ApiLiveStockAppraisalResponse
import com.example.akhuwatdemo.model.ApiLoanUtilizationResponse
import com.example.akhuwatdemo.model.ApiLoginRequest
import com.example.akhuwatdemo.model.ApiLoginResponse
import com.example.akhuwatdemo.model.ApiRegionResponse
import com.example.akhuwatdemo.model.Data
import com.example.akhuwatdemo.model.Result
import com.example.akhuwatdemo.model.ResultX
import com.example.akhuwatdemo.model.ResultXX
import com.example.akhuwatdemo.model.ResultXXX
import com.example.akhuwatdemo.model.ResultXXXX
import com.example.akhuwatdemo.model.ResultXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXXXX
import com.example.akhuwatdemo.model.ResultXXXXXXXXXXXX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDataFromApi {

    fun loginUser(body: ApiLoginRequest, context: Context, data: ApiResponseBack<Data>) {

        RetrofitClient.instance(context).login(body).enqueue(object : Callback<ApiLoginResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<ApiLoginResponse>,
                response: Response<ApiLoginResponse>
            ) {
                val code = response.code()
                if (response.body()?.StatusCode?.equals(code) == true) {
                    val token = response.body()!!.Data.Token.Token
                    val n = SharedPref.getInstance(context)
                    n.saveToken(token).toString()

                    val userrr = response.body()!!.Data
                    val sharedPref = SharedPref.getInstance(context)
                    sharedPref.saveUserToSharedPreferences(context, userrr)

                    data.onSuccess(response.body()!!.Data)

                } else {
                    data.onFailure("Error")
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ApiLoginResponse>, t: Throwable) {
                data.onFailure(t.message.toString())

            }

        })
    }

    fun getBusiness(context: Context, data: ApiResponseBack<List<Result>>) {
        RetrofitClient.instance(context).getBusiness()
            .enqueue(object : Callback<ApiBusinesssResponse> {
                override fun onResponse(
                    call: Call<ApiBusinesssResponse>, response: Response<ApiBusinesssResponse>
                ) {
                    if(response != null) {
                        if(response.body()!=null){
                            if(response.body()!!.result != null){
                                data.onSuccess(response.body()!!.result)
                            }

                        }

                    }

                }

                override fun onFailure(call: Call<ApiBusinesssResponse>, t: Throwable) {
                    data.onFailure(t.message.toString())
                }


            })
    }

    fun getRegion(businessID:String , context: Context, data: ApiResponseBack<List<ResultX>>){

        RetrofitClient.instance(context).getRegion(businessID).enqueue(object : Callback<ApiRegionResponse>{
            override fun onResponse(
                call: Call<ApiRegionResponse>,
                response: Response<ApiRegionResponse>
            ) {
                if(response != null) {
                    if(response.body()!=null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }

                    }

                }

            }

            override fun onFailure(call: Call<ApiRegionResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })

    }

    fun getArea(businessID:String , regionID: String, context: Context, data:ApiResponseBack<List<ResultXX>>){
        RetrofitClient.instance(context).getArea(regionID,businessID).enqueue(object : Callback<ApiAreaResponse>{
            override fun onResponse(
                call: Call<ApiAreaResponse>,
                response: Response<ApiAreaResponse>
            ) {
                if(response != null) {
                    if(response.body()!=null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }

                    }

                }

            }

            override fun onFailure(call: Call<ApiAreaResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

    fun getBranch(businessID: String, areaID : String, context: Context, data:ApiResponseBack<List<ResultXXX>>){
        RetrofitClient.instance(context).getBranch(areaID,businessID).enqueue(object : Callback<ApiBranchResponse>{
            override fun onResponse(
                call: Call<ApiBranchResponse>,
                response: Response<ApiBranchResponse>
            ) {
                if(response != null) {
                    if(response.body()!=null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }

                    }

                }

            }

            override fun onFailure(call: Call<ApiBranchResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

    fun getBusinessPlace(businessID : String, context: Context, data :  ApiResponseBack<List<ResultXXXX>>){
        RetrofitClient.instance(context).getBusinessPlace(businessID).enqueue(object : Callback<ApiBusinessPlaceResponse> {
            override fun onResponse(
                call: Call<ApiBusinessPlaceResponse>,
                response: Response<ApiBusinessPlaceResponse>
            ) {
                if(response != null) {
                    if(response.body()!=null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }

                    }

                }

            }

            override fun onFailure(call: Call<ApiBusinessPlaceResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })

    }

    fun getLoanUtilization(businessID: String,context: Context,data:ApiResponseBack<List<ResultXXXXX>>){
        RetrofitClient.instance(context).getLoanUtilization(businessID).enqueue(object : Callback<ApiLoanUtilizationResponse>{
            override fun onResponse(
                call: Call<ApiLoanUtilizationResponse>,
                response: Response<ApiLoanUtilizationResponse>
            ) {
                if(response != null) {
                    if(response.body()!=null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }

                    }

                }

            }

            override fun onFailure(call: Call<ApiLoanUtilizationResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }
    fun getLiveStockAppraisal(businessID: String, applicationID : String, context: Context, data:ApiResponseBack<ResultXXXXXX>){
        RetrofitClient.instance(context).getLivestockAppraisal(applicationID,businessID).enqueue(object : Callback<ApiLiveStockAppraisalResponse>{
            override fun onResponse(
                call: Call<ApiLiveStockAppraisalResponse>,
                response: Response<ApiLiveStockAppraisalResponse>
            ) {
                if(response != null) {
                    if(response.body()!=null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }

                    }

                }
            }

            override fun onFailure(call: Call<ApiLiveStockAppraisalResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

    fun getAgriAppraisalType(businessID: String, context: Context, data: ApiResponseBack<List<ResultXXXXXXX>>){
        RetrofitClient.instance(context).getAgriAppraisalType(businessID).enqueue(object : Callback<ApiAgriAppraisalTypeResponse>{
            override fun onResponse(
                call: Call<ApiAgriAppraisalTypeResponse>,
                response: Response<ApiAgriAppraisalTypeResponse>
            ) {
                if(response != null){
                    if(response.body() != null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiAgriAppraisalTypeResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

    fun getAreaTypeData(businessID: String, context: Context, data : ApiResponseBack<List<ResultXXXXXXXX>>){
        RetrofitClient.instance(context).getAreaType(businessID).enqueue(object : Callback<ApiAreaTypeResponse>{
            override fun onResponse(
                call: Call<ApiAreaTypeResponse>,
                response: Response<ApiAreaTypeResponse>
            ) {
                if(response != null){
                    if(response.body() != null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiAreaTypeResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

    fun getIrrigationSourceType(businessID: String, context: Context, data : ApiResponseBack<List<ResultXXXXXXXXX>>){
        RetrofitClient.instance(context).getIrrigationSourceType(businessID).enqueue(object : Callback<ApiIrrigationSourceTypeResponse>{
            override fun onResponse(
                call: Call<ApiIrrigationSourceTypeResponse>,
                response: Response<ApiIrrigationSourceTypeResponse>
            ) {
                if(response != null){
                    if(response.body() != null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiIrrigationSourceTypeResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

//    fun getLoanPurposeType(businessID: String, context: Context, data: ApiResponseBack<List<ResultXXXXXXXXXX>>){
//        RetrofitClient.instance(context).getLoanPurposeData(businessID).enqueue(object : Callback<ApiLoanPurposeTypeResponse>{
//            override fun onResponse(
//                call: Call<ApiLoanPurposeTypeResponse>,
//                response: Response<ApiLoanPurposeTypeResponse>
//            ) {
//                if(response != null){
//                    if(response.body() != null){
//                        if(response.body()!!.result != null){
//                            data.onSuccess(response.body()!!.result)
//                        }
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<ApiLoanPurposeTypeResponse>, t: Throwable) {
//                data.onFailure(t.message.toString())
//            }
//
//        })
//    }

        fun getFarmSeasonType(businessID: String, context: Context, data: ApiResponseBack<List<ResultXXXXXXXXXXX>>){
            RetrofitClient.instance(context).getFarmSeasonTypeData(businessID).enqueue(object : Callback<ApiFarmSeasonTypeResponse>{
                override fun onResponse(
                    call: Call<ApiFarmSeasonTypeResponse>,
                    response: Response<ApiFarmSeasonTypeResponse>
                ) {
                    if(response != null){
                        if(response.body() != null){
                            if(response.body()!!.result != null){
                                data.onSuccess(response.body()!!.result)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ApiFarmSeasonTypeResponse>, t: Throwable) {
                    data.onFailure(t.message.toString())
                }

            })
        }

    fun getAgriAppraisal(businessID: String, applicationID: String, context: Context, data: ApiResponseBack<ResultXXXXXXXXXXXX>) {

        RetrofitClient.instance(context).getAgriAppraisal(businessID,applicationID).enqueue(object : Callback<ApiAgriAppraisalResponse>{
            override fun onResponse(
                call: Call<ApiAgriAppraisalResponse>,
                response: Response<ApiAgriAppraisalResponse>
            ) {
                if(response != null){
                    if(response.body() != null){
                        if(response.body()!!.result != null){
                            data.onSuccess(response.body()!!.result)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiAgriAppraisalResponse>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }

}


