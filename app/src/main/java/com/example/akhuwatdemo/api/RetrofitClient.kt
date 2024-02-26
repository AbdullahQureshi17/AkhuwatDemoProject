package com.example.akhuwatdemo.api

import android.annotation.SuppressLint
import android.content.Context
import com.example.akhuwatdemo.utils.SharedPref

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RetrofitClient private constructor(val context: Context){



    companion object{

        private const val BASE_URL = "http://10.84.12.105:5118/"
        private lateinit var sharePref : SharedPref

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "Bearer "+sharePref.getToken())
                        .addHeader("Accept", "application/json")
                        .method(original.method(), original.body())


                val request = requestBuilder.build()
                chain.proceed(request)


            }.build()

        fun instance(context: Context): Api {
            sharePref = SharedPref.getInstance(context)
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(Api::class.java)
        }

    }



}




