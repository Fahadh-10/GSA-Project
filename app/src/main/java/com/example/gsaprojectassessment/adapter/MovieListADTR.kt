package com.example.gsaprojectassessment.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsaprojectassessment.model.Movie
import com.example.gsaprojectassessment.movieVH.CategoriesViewHolder
import com.example.gsaprojectassessment.movieVH.FeaturedViewHolder

class MovieListADTR(var movie: ArrayList<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (Movie.MovieHomeListType.getByPosition(viewType)) {
            Movie.MovieHomeListType.FEATURED -> FeaturedViewHolder.getViewHolder(parent)
            Movie.MovieHomeListType.CATEGORIES -> CategoriesViewHolder.getViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun getItemViewType(position: Int): Int {
        return Movie.MovieHomeListType.getHomeListViewType(movie[position])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FeaturedViewHolder -> holder.configureCell(movie, context)
            is CategoriesViewHolder -> holder.configureCell(movie , context)
        }
    }
}