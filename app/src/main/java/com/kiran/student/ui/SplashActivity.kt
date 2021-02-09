package com.kiran.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kiran.student.R
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.repository.UserRepository
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private var username:String=""
    private var password:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            try {
                getSharedPref()
                val repo = UserRepository()
                val response =repo.loginUser(username,password)
                if (response.success==true){
                    ServiceBuilder.token="Bearer "+response.token
                    startActivity(
                        Intent(
                            this@SplashActivity,
                            DashboardActivity::class.java
                        )
                    )
                    finish()
                }
                else{
                    startActivity(
                        Intent(
                            this@SplashActivity,
                            LoginActivity::class.java
                        )
                    )
                }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@SplashActivity,
                        "Login error", Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
    }
    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
         username = sharedPref.getString("username", "").toString()
         password = sharedPref.getString("password", "").toString()
    }


}