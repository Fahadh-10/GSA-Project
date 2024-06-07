package com.example.gsaprojectassessment.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsaprojectassessment.model.HomeMovieList
import com.example.gsaprojectassessment.movieVH.CategoriesViewHolder
import com.example.gsaprojectassessment.movieVH.FeaturedViewHolder

class MovieListADTR(var movie: List<HomeMovieList>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (HomeMovieList.MovieHomeListType.getByPosition(viewType)) {
            HomeMovieList.MovieHomeListType.FEATURED -> FeaturedViewHolder.getViewHolder(parent)
            HomeMovieList.MovieHomeListType.CATEGORIES -> CategoriesViewHolder.getViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun getItemViewType(position: Int): Int {
        return HomeMovieList.MovieHomeListType.getHomeListViewType(movie[position])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FeaturedViewHolder -> holder.configureCell(movie[position].homeMovieList, context)
            is CategoriesViewHolder -> holder.configureCell(movie[position].homeMovieList , context)
        }
    }
}