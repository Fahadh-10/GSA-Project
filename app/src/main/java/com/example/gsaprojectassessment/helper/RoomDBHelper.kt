package com.example.gsaprojectassessment.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
                )
//                    .addMigrations(migration1to2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

//val migration1to2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE Movie ADD COLUMN movieHomeListType TEXT NOT NULL DEFAULT ''")
//    }
//}