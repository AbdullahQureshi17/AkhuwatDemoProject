package com.example.akhuwatdemo.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.akhuwatdemo.callback.ApiResponseBack
import com.example.akhuwatdemo.model.ApiLoginResponse
import com.example.akhuwatdemo.model.Data
import com.example.akhuwatdemo.model.User
import com.example.akhuwatdemo.model.Users
import com.google.gson.Gson

class SharedPref private constructor() {


    companion object {
        private val sharePref = SharedPref()
        private lateinit var sharedPreferences: SharedPreferences
        const val USER_TOKEN = "USER_TOKEN"
        const val BUSINESS_ID = "BUSINESS_ID"
        const val REGION_ID = "REGION_ID"
        const val AREA_ID = "AREA_ID"
        const val BRANCH_ID = "BRANCH_ID"
        const val LOAN_ID = "LOAN_ID"

        private val PLACE_OBJ = "place_obj"

        fun getInstance(context: Context): SharedPref {
            if (!Companion::sharedPreferences.isInitialized) {
                synchronized(SharedPref::class.java) {
                    if (!Companion::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }

    }



    fun saveUserToSharedPreferences(context: Context, user: Data) {
        val editor = sharedPreferences.edit()

        // Use Gson to convert the Person object to a JSON string
        val gson = Gson()
        val userJson = gson.toJson(user)

        // Save the JSON string to SharedPreferences
        editor.putString("user_key", userJson)
        editor.apply()
    }

    fun getUserFromSharedPreferences(context: Context): Data? {
        // Retrieve the JSON string from SharedPreferences
        val userJson = sharedPreferences.getString("user_key", null)

        // Use Gson to convert the JSON string back to a Person object
        val gson = Gson()
        return gson.fromJson(userJson, Data::class.java)
    }

    fun saveUser(user: Users) {

        val editor = sharedPreferences.edit()
        editor.putString("UserName", user.UserName)
        editor.putString("Token", user.Token)
        editor.apply()
    }

    fun getUser(): Users {
        return Users(
            sharedPreferences.getString("UserName", "").orEmpty(),
            sharedPreferences.getString("Token", "").orEmpty()
        )
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getInt("id", -1) != -1
    }



    fun clear(context: Context){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun saveToken(token : String){
        val editor = sharedPreferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken() : String {
        return sharedPreferences.getString(USER_TOKEN, "").toString()
    }

    fun saveBusinessID(businessID : String){
        val editor = sharedPreferences.edit()
        editor.putString(BUSINESS_ID, businessID)
        editor.apply()
    }
    fun getBusinessID() : String {
        return sharedPreferences.getString(BUSINESS_ID,"").toString()
    }

    fun saveRegionID(regionID : String){
        val editor = sharedPreferences.edit()
        editor.putString(REGION_ID, regionID)
        editor.apply()
    }
    fun getRegionID() : String {
        return sharedPreferences.getString(REGION_ID,"").toString()
    }

    fun saveAreaID(areaID : String){
        val editor = sharedPreferences.edit()
        editor.putString(AREA_ID,areaID)
        editor.apply()
    }

    fun getAreaID() : String {
        return sharedPreferences.getString(AREA_ID,"").toString()
    }

    fun saveBranchID(branchID : String){
        val editor = sharedPreferences.edit()
        editor.putString(BRANCH_ID,branchID)
        editor.apply()

    }

    fun getBranchID() : String{
        return sharedPreferences.getString(BRANCH_ID,"").toString()
    }

    fun saveLoanID(loanID : String){
        val editor = sharedPreferences.edit()
        editor.putString(LOAN_ID, loanID)
        editor.apply()
    }

    fun getLoanID() : String
    {
        return sharedPreferences.getString(LOAN_ID,"").toString()
    }


}