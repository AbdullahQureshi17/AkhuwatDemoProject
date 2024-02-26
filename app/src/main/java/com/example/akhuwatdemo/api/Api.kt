package com.example.akhuwatdemo.api

import com.example.akhuwatdemo.model.ApiAgriAppraisalRequest
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
import com.example.akhuwatdemo.model.ApiLivestockAppraisalRequest
import com.example.akhuwatdemo.model.ApiLoanPurposeTypeResponse
import com.example.akhuwatdemo.model.ApiLoanUtilizationResponse
import com.example.akhuwatdemo.model.ApiLoginRequest
import com.example.akhuwatdemo.model.ApiLoginResponse
import com.example.akhuwatdemo.model.ApiRegionResponse
import com.example.akhuwatdemo.model.DefaultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @FormUrlEncoded
    @POST("createuser")
    fun createUser(
        @Field("First Name") firstName : String,
        @Field("Last Name") lastName : String,
        @Field("UserName") UserName : String,
        @Field("Password") Password : String
    ) : Call<DefaultResponse>


    @POST("authentication/api/v1/authentication/login")
    fun login(@Body body : ApiLoginRequest) : Call<ApiLoginResponse>



    @GET("setup/api/v1/business/getbusinessesbyactivelist")
    fun getBusiness() : Call<ApiBusinesssResponse>


    @GET("setup/api/v1/region/getregionsbyactivelist/{businessID}")
    fun getRegion(@Path("businessID") businessID : String) : Call<ApiRegionResponse>

    @GET("setup/api/v1/area/getareasbyregionid/{regionId}/{businessID}")
    fun getArea(@Path("regionId") regionId: String, @Path("businessID") businessID : String) : Call<ApiAreaResponse>

    @GET("setup/api/v1/branch/getbranchesbyareaid/{areaId}/{businessID}")
    fun getBranch(@Path("areaId") areaId : String, @Path("businessID") businessID : String) : Call<ApiBranchResponse>

    @GET("setup/api/v1/businessplacetype/getbusinessplacetypesbyactivelist/{businessId}")
    fun getBusinessPlace(@Path("businessId") businessId : String)  : Call<ApiBusinessPlaceResponse>

    @GET("setup/api/v1/loanutilizationtype/getloanutilizationtypesbyactivelist/{businessId}")
    fun getLoanUtilization(@Path("businessId") businessID: String) : Call<ApiLoanUtilizationResponse>

    @POST("appraisal/api/v1/livestock/appraisal/savelivestockappraisal")
    fun setLivestockAppraisal(@Body liveStock : ApiLivestockAppraisalRequest) : Call<ApiLivestockAppraisalRequest>

    @GET("appraisal/api/v1/livestock/appraisal/getlivestockappraisalbyapplicationid/{applicationId}/{businessId}")
    fun getLivestockAppraisal(@Path("applicationId") applicationId : String,@Path("businessId") businessId: String) : Call<ApiLiveStockAppraisalResponse>

    @GET("/setup/api/v1/agriappraisaltype/getagriappraisaltypesbyactivelist/{businessId}")
    fun getAgriAppraisalType(@Path("businessId") businessId: String) : Call<ApiAgriAppraisalTypeResponse>

    @GET("/setup/api/v1/areatype/getareatypesbyactivelist/{businessId}")
    fun getAreaType(@Path("businessId") businessId: String) : Call<ApiAreaTypeResponse>

    @GET("/setup/api/v1/irrigationsourcetype/getirrigationsourcetypesbyactivelist/{businessId}")
    fun getIrrigationSourceType(@Path("businessId") businessId: String) : Call<ApiIrrigationSourceTypeResponse>

    @GET("/setup/api/v1/loanpurpose/getloanpurposesbyactivelist/{businessId}")
    fun getLoanPurposeData(@Path("businessId") businessId: String) : Call<ApiLoanPurposeTypeResponse>

    @POST("/appraisal/api/v1/agriculture/appraisal/saveagricultureappraisal")
    fun setAgriAppraisal(@Body agriculture : ApiAgriAppraisalRequest) : Call<ApiAgriAppraisalRequest>

    @GET("/setup/api/v1/farmseasontype/getfarmseasontypesbyactivelist/{businessId}")
    fun getFarmSeasonTypeData(@Path("businessId") businessId: String) : Call<ApiFarmSeasonTypeResponse>

    @GET("/appraisal/api/v1/agriculture/appraisal/getagriappraisalbyapplicationid/{applicationId}/{businessId}")
    fun getAgriAppraisal(@Path("businessId") businessId: String, @Path("applicationId") applicationId: String) : Call<ApiAgriAppraisalResponse>
}