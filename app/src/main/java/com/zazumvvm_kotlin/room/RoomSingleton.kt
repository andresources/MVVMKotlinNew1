package com.zazumvvm_kotlin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zazumvvm_kotlin.room.dao.StudentDao
import com.zazumvvm_kotlin.room.entities.Student

@Database(entities = [Student::class], version = 1)
abstract class RoomSingleton :RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context: Context): RoomSingleton {
            /* if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")
                    .build()
            }
            return INSTANCE as RoomSingleton*/
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}
