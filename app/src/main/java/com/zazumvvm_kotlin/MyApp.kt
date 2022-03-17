package com.zazumvvm_kotlin

import android.app.Application
import com.zazumvvm_kotlin.repository.StudentRep
import com.zazumvvm_kotlin.room.RoomSingleton

class MyApp :Application() {
    val database by lazy { RoomSingleton.getInstance(this)}
    val repository by lazy { StudentRep(database.studentDao()) }
}