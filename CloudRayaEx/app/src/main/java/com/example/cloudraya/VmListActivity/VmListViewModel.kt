package com.example.cloudraya.VmListActivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cloudraya.API.ApiService
import com.example.cloudraya.Local.SiteDao
import com.example.cloudraya.Local.SiteDatabase
import com.example.cloudraya.Local.SiteRegister
import com.example.cloudraya.Model.ResponseVmList
import com.example.cloudraya.Model.UserResponse
import com.example.cloudraya.ModelApiTest.DetailItem
import com.example.cloudraya.ModelApiTest.ResponseLogin
import com.example.cloudraya.Util.NetworkResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VmListViewModel(application: Application): AndroidViewModel(application) {

    private var siteDao : SiteDao?
    private var siteDb : SiteDatabase?

    init {
        siteDb = SiteDatabase.getDatabase(application)
        siteDao = siteDb?.SiteDao()
    }

    var userResponseVmList = MutableLiveData<ResponseVmList>()
    var userLogin = MutableLiveData<DetailItem>()

//    fun login(apiUrl: String, token: String){
//        ApiService(apiUrl).instanceRetrofit.login1(token).enqueue(object: Callback<DetailItem> {
//            override fun onResponse(
//                call: Call<DetailItem>,
//                response: Response<DetailItem>,
//            ) {
//                if (response.isSuccessful ){
////                    Toast.makeText(this@RegisterActivity, respon.message, Toast.LENGTH_SHORT).show()
//                    userLogin.postValue(response.body())
//
//                }else{
//                    Log.e("gagal", response.message())
////                    Toast.makeText(this@RegisterActivity, "your credential not valid", Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<DetailItem>, t: Throwable) {
//                Log.e("error", t.message!!)
//            }
//        })
//    }


}