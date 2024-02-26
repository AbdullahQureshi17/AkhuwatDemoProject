package com.example.akhuwatdemo.utils

import android.content.Context
import com.example.akhuwatdemo.api.RetrofitClient
import com.example.akhuwatdemo.callback.ApiResponseBack
import com.example.akhuwatdemo.model.ApiAgriAppraisalRequest
import com.example.akhuwatdemo.model.ApiLivestockAppraisalRequest
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendDataToApi {

        fun sendData(body : ApiLivestockAppraisalRequest, context: Context, data: ApiResponseBack<ApiLivestockAppraisalRequest>){

            val gson = Gson()
            val userJson = gson.toJson(body)

            RetrofitClient.instance(context).setLivestockAppraisal(body).enqueue(object : Callback<ApiLivestockAppraisalRequest>{
                override fun onResponse(
                    call: Call<ApiLivestockAppraisalRequest>,
                    response: Response<ApiLivestockAppraisalRequest>
                ) {
                      response.body()?.let { data.onSuccess(it) }

                }

                override fun onFailure(call: Call<ApiLivestockAppraisalRequest>, t: Throwable) {
                        data.onFailure(t.message.toString())
                }

            })

        }

    fun sendAgriAppraisalData(agriAppraisalData : ApiAgriAppraisalRequest, context: Context, data: ApiResponseBack<ApiAgriAppraisalRequest>){
        val gson = Gson()
        val userJson = gson.toJson(agriAppraisalData)

        RetrofitClient.instance(context).setAgriAppraisal(agriAppraisalData).enqueue(object : Callback<ApiAgriAppraisalRequest>{
            override fun onResponse(
                call: Call<ApiAgriAppraisalRequest>,
                response: Response<ApiAgriAppraisalRequest>
            ) {
                response.body()?.let { data.onSuccess(it) }
            }

            override fun onFailure(call: Call<ApiAgriAppraisalRequest>, t: Throwable) {
                data.onFailure(t.message.toString())
            }

        })
    }
}