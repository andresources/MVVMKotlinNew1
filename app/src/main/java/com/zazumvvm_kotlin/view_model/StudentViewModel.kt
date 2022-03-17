package com.zazumvvm_kotlin.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zazumvvm_kotlin.MyApp
import com.zazumvvm_kotlin.repository.StudentRep
import com.zazumvvm_kotlin.room.RoomSingleton
import com.zazumvvm_kotlin.room.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    //private val db:RoomSingleton = RoomSingleton.getInstance(application)
    lateinit var students : LiveData<List<Student>>
    lateinit var srep:StudentRep;
    init {
        /*val db:RoomSingleton = RoomSingleton.getInstance(application)
        students = db.studentDao().getStudents().asLiveData()
        srep=StudentRep(db.studentDao())*/
        srep=(application as MyApp).repository
        students = srep.allStudents.asLiveData()
    }
    fun insert(student: Student)=
        viewModelScope.launch(Dispatchers.IO)  {
            srep.insertStudent(student)
        }
    //= or {}
    fun clear(){
        viewModelScope.launch(Dispatchers.IO) {
            srep.clearStudents()
        }
    }
}