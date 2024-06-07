package com.example.gsaprojectassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsaprojectassessment.Dao.MovieDao
import com.example.gsaprojectassessment.adapter.MovieListADTR
import com.example.gsaprojectassessment.databinding.ActivityMainBinding
import com.example.gsaprojectassessment.helper.RoomDBHelper
import com.example.gsaprojectassessment.model.HomeMovieList
import com.example.gsaprojectassessment.model.Movie
import com.example.gsaprojectassessment.model.SearchResult
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieListADTR: MovieListADTR
    var movies = ArrayList<Movie>()
    private lateinit var apiService: APIService
    private lateinit var roomDBHelper : RoomDBHelper
    private lateinit var movieDao : MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        roomDBHelper = RoomDBHelper.getDataBase(this@MainActivity)
        movieDao = roomDBHelper.movieDao()
        setAdapter()
        apiService = RetrofitManager.instance.create(APIService::class.java)
        getMovies()
    }

    /**
     * Sets up the RecyclerView adapter and click listeners.
     */
    private fun setAdapter() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movieListADTR = MovieListADTR(ArrayList())
        binding.recyclerView.adapter = movieListADTR
    }


    private fun getMovies() {
        val apiKey = "5d81e1ce"
        val type = "movie"
        val page = 1
        val searchQuery = "guardians"
        val call = apiService.getMovies(type, apiKey, page, searchQuery)
        call.enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if (response.isSuccessful) {
                    val fetchedMovies = response.body()?.search ?: ArrayList()
                    val categoryList = ArrayList<Movie>()
                    val featureList = ArrayList<Movie>()
                    val homeMovieList = ArrayList<HomeMovieList>()

                    fetchedMovies.forEach { movie ->
                        categoryList.add(movie)
                    }
                    homeMovieList.add(HomeMovieList(categoryList , HomeMovieList.MovieHomeListType.CATEGORIES.name))

                    fetchedMovies.forEach { movie ->
                        featureList.add(movie)
                    }
                    homeMovieList.add(HomeMovieList(featureList , HomeMovieList.MovieHomeListType.FEATURED.name))

//                    CoroutineScope(Dispatchers.IO).launch {
//                        movieDao.insert(fetchedMovies)
//
//                        // Once data is inserted, fetch it from the database
//                        val staffList = movieDao.getAllStaff()
//
//                        // Update UI on the main thread
//                        withContext(Dispatchers.Main) {
//                            // Update your UI with the fetched data here
//                            // For example, you can update your adapter with the fetched data
////                            movieListADTR.movie = staffList
////                            movieListADTR.notifyDataSetChanged()
//                        }
//                    }
                    movieListADTR.movie = homeMovieList
                    movieListADTR.notifyDataSetChanged()
                } else {
                    Log.e("MainActivity", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }


}