package com.example.gsaprojectassessment.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gsaprojectassessment.model.HomeMovieList
import com.example.gsaprojectassessment.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(staff: List<Movie>)


    @Query("SELECT * FROM Movie")
    suspend fun getAllStaff() :  List<Movie>
}
