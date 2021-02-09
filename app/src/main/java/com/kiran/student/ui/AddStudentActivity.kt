package com.kiran.student.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.kiran.student.R
import com.kiran.student.entity.Student
import com.kiran.student.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddStudentActivity : AppCompatActivity() {
    private lateinit var fname:EditText
    private lateinit var Age:EditText
    private lateinit var Address:EditText
    private lateinit var rdoMale: RadioButton
    private lateinit var rdoFeMale: RadioButton
    private lateinit var rdoOther: RadioButton
    private lateinit var btnsave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        fname=findViewById(R.id.etFirstname)
        Age=findViewById(R.id.etAge)
        Address=findViewById(R.id.etAddress)
        rdoMale=findViewById(R.id.rdoMale)
        rdoFeMale=findViewById(R.id.rdoFemale)
        rdoOther=findViewById(R.id.rdoOther)
        btnsave=findViewById(R.id.btnSave)

        btnsave.setOnClickListener {
          saveStudent()
            }
        }
    private fun saveStudent(){
        val fullName = fname.text.toString()
        val age = Age.text.toString().toInt()
        val address = Address.text.toString()
        var gender = ""
        when {
            rdoFeMale.isChecked -> {
                gender = "Female"
            }
            rdoMale.isChecked -> {
                gender = "Male"
            }
            rdoOther.isChecked -> {
                gender = "Others"
            }
        }

        val student=Student(fullname=fullName,age=age,gender = gender,address = address)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val studentRepository=StudentRepository()
                val response=studentRepository.addStudent(student)
                if (response.success==true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddStudentActivity,
                            "Add bhayo", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            catch (ex: Exception) {
                withContext(context = Dispatchers.Main) {
                    Toast.makeText(
                        this@AddStudentActivity,
                        "Bhako XAINa", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    }
