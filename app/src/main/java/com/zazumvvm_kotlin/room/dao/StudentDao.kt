package com.zazumvvm_kotlin.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zazumvvm_kotlin.room.entities.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM studentTbl ORDER BY id DESC")
    fun getStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student:Student) : Long

    @Query("DELETE FROM studentTbl")
    suspend fun clear() : Void
}