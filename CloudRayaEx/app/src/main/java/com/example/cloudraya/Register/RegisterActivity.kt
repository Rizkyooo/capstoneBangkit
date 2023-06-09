package com.example.cloudraya.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cloudraya.API.ApiService
import com.example.cloudraya.Local.SiteRegister
import com.example.cloudraya.Model.UserRequest
import com.example.cloudraya.Model.UserResponse
import com.example.cloudraya.Service.FirebaseMessagingService
import com.example.cloudraya.SiteList.SiteListActivity
import com.example.cloudraya.databinding.ActivityRegisterBinding
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    lateinit var firebaseMessagingService: FirebaseMessagingService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRegisterSite.setOnClickListener {
                val siteName = siteName.text.toString()
                val apiUrl = apiUrl.text.toString()
                val appKey = appKey.text.toString()
                val secretKey = secretKey.text.toString()
                val request = UserRequest()
                request.app_key = appKey
                request.secret_key = secretKey
                intent = Intent(this@RegisterActivity, SiteListActivity::class.java)
                startActivity(intent)

                firebaseMessagingService = FirebaseMessagingService()
                FirebaseMessaging.getInstance().token.addOnCompleteListener {
                    if (it.isSuccessful){
                        val fcmToken = it.result
                        viewModel.register(apiUrl,fcmToken,siteName,appKey,secretKey)
                    }
                }

            }
        }
    }

}