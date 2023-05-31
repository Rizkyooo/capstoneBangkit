package com.example.cloudraya.API

import com.example.cloudraya.Local.SiteRegister
import com.example.cloudraya.Model.*
import com.example.cloudraya.ModelApiTest.DetailItem
import com.example.cloudraya.ModelApiTest.ResponseLogin
import com.google.android.gms.tasks.Task
import com.google.gson.annotations.Expose
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {
    @Headers("Content-Type:application/json")
    @POST("/v1/api/gateway/user/auth")
    fun login(
        @Body userRequest: Task<String>
    ): Call <UserResponse>

    @GET("/v1/api/gateway/user/virtualmachines")
    fun getVmList(@Header("Authorization") authorization: String): Call<ResponseVmList>

    @GET("/v1/api/gateway/user/virtualmachines/{id}")
    fun getVMDetail(@Header("Authorization") authorization: String, @Path("id") vmId: Int): Call<ResponseDetailVm>

    @POST("/v1/api/gateway/user/virtualmachines/action")
    fun actionVM(@Header("Authorization") authorization: String, @Body requestActionVm: ResquestActionVm) :Call<ResponseActionVm>

    @FormUrlEncoded
    @POST("/v1/api/gateway/user")
    fun login1(
        @Field("token") token: String
    ): Call<ResponseBody>

    @GET("/v1/api/gateway/user/virtualmachines")
    fun getVmList(): Call<com.example.cloudraya.ModelApiTest.ResponseVmList>
}