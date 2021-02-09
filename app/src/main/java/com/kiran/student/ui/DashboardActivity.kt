package com.kiran.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kiran.student.R

class DashboardActivity : AppCompatActivity() {
    private lateinit var btnAdd:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnAdd=findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent=Intent(this,AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}