package com.example.akhuwatdemo.model

object Constants {
    fun getFarmerData():ArrayList<FarmerType>{
        // create an arraylist of type employee class
        val farmerList=ArrayList<FarmerType>()
        val f1=FarmerType(false,"Owner")
        farmerList.add(f1)
        val f2=FarmerType(false,"Tenant")
        farmerList.add(f2)
        val f3=FarmerType(false,"Renter")
        farmerList.add(f3)
        val f4=FarmerType(false,"Seller")
        farmerList.add(f4)


        return farmerList
    }
}