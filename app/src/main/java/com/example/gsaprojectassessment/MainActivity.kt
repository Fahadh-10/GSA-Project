package com.example.gsaprojectassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsaprojectassessment.Dao.MovieDao
import com.example.gsaprojectassessment.adapter.MovieListADTR
import com.example.gsaprojectassessment.databinding.ActivityMainBinding
import com.example.gsaprojectassessment.helper.RoomDBHelper
import com.example.gsaprojectassessment.model.Movie
import com.example.gsaprojectassessment.model.SearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieListADTR: MovieListADTR
    var movies = ArrayList<Movie>()
    private lateinit var apiService: APIService
    private lateinit var roomDBHelper : RoomDBHelper
    private lateinit var movieDao :MovieDao

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
        movieListADTR = MovieListADTR(movies)
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
                    val updatedMovies = ArrayList<Movie>()

                    fetchedMovies.forEach { movie ->
                        val featuredMovie = movie.copy(movieHomeListType = Movie.MovieHomeListType.FEATURED.name)
                        updatedMovies.add(featuredMovie)
                    }

                    fetchedMovies.forEach { movie ->
                        val categoriesMovie = movie.copy(movieHomeListType = Movie.MovieHomeListType.CATEGORIES.name)
                        updatedMovies.add(categoriesMovie)
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        movieDao.insert(updatedMovies)
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        val staffListDeferred = async(Dispatchers.IO) {
                            movieDao.getAllStaff()
                        }
                    }
                    movieListADTR.movie = updatedMovies
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