package com.zazumvvm_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zazumvvm_kotlin.room.entities.Student
import com.zazumvvm_kotlin.view_model.StudentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var model: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.movementMethod = ScrollingMovementMethod()
        // initialize the student view model
        model = ViewModelProvider(this).get(StudentViewModel::class.java)
        // observe the students live data
        model.students.observe(this, Observer { students->
            textView.text = "Students(${students.size})..."
            students.forEach {
                textView.append("\n${it.id} | ${it.fullName} : ${it.result}")
            }
        }
        )
    }

    fun getName(view: View) {
        //var name=ApisConstants.getName("Harina")
        //var emp  = Employee("Hari",3000)
        //Toast.makeText(applicationContext,name,Toast.LENGTH_LONG).show()
    }

    fun insertData(view: View) {
        val student = Student(
            null,
            UUID.randomUUID().toString(),5
        )
        model.insert(student)
    }
    fun clearData(view: View) {
        model.clear()
    }
}