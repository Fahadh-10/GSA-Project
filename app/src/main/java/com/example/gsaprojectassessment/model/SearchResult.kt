package com.example.gsaprojectassessment.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search") var search: ArrayList<Movie>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)

data class HomeMovieList(
    var homeMovieList : ArrayList<Movie> ?= ArrayList(),
    var type : String ?= null
) {
    enum class MovieHomeListType {
        FEATURED,
        CATEGORIES;

        companion object {
            fun getHomeListViewType(homeList: HomeMovieList): Int {
                return when (homeList.getHomeListType()) {
                    FEATURED -> FEATURED.ordinal
                    CATEGORIES -> CATEGORIES.ordinal
                }
            }

            fun getByPosition(value: Int): MovieHomeListType {
                return entries.firstOrNull { it.ordinal == value } ?: FEATURED
            }
        }
    }

    fun getHomeListType(): MovieHomeListType {
        return MovieHomeListType.valueOf(type?.uppercase() ?: "")
    }
}

@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey(autoGenerate = true) var movieId: Int = 0,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
    var movieHomeListType: String= "default_value_here"
)