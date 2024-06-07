package com.example.gsaprojectassessment.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gsaprojectassessment.Dao.MovieDao
import com.example.gsaprojectassessment.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class RoomDBHelper : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null

        fun getDataBase(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDBHelper::class.java,
                    "staff_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}