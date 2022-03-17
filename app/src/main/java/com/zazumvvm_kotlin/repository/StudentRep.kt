package com.zazumvvm_kotlin.repository

import androidx.annotation.WorkerThread
import com.zazumvvm_kotlin.room.dao.StudentDao
import com.zazumvvm_kotlin.room.entities.Student
import kotlinx.coroutines.flow.Flow

class StudentRep(private val sdao : StudentDao) {
    val allStudents: Flow<List<Student>> = sdao.getStudents()
    @WorkerThread
    suspend fun insertStudent(student:Student){
        sdao.insert(student)
    }
    @WorkerThread
    suspend fun clearStudents(){
        sdao.clear()
    }
}