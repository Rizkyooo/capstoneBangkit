package com.example.cloudraya.Register

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cloudraya.API.ApiService
import com.example.cloudraya.Local.SiteDao
import com.example.cloudraya.Local.SiteDatabase
import com.example.cloudraya.Local.SiteRegister
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private var siteDao : SiteDao?
    private var siteDb : SiteDatabase?

    init {
        siteDb = SiteDatabase.getDatabase(application)
        siteDao = siteDb?.SiteDao()
    }

    fun addSite(site: SiteRegister){
        viewModelScope.launch {
            siteDao?.insert(site)
        }
    }

    fun register(apiUrl: String, token: String, siteName: String, appKey: String, secretKey: String){
        ApiService(apiUrl).instanceRetrofit.login1(token).enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>,
            ) {
                if (response.isSuccessful ){
                    val userSite = SiteRegister(site_name = siteName, api_url = apiUrl, app_key = appKey, secret_key = secretKey, bearer_token = token)
                    addSite(userSite)
                }else{
                    Log.e("gagal", response.message())
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("error", t.message!!)
            }
        })
    }

}