package com.example.gsaprojectassessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsaprojectassessment.databinding.CategoryListItemBinding
import com.example.gsaprojectassessment.databinding.FeatureListItemBinding
import com.example.gsaprojectassessment.model.Movie
import com.squareup.picasso.Picasso

class FeaturesADTR(var movie: ArrayList<Movie>) : RecyclerView.Adapter<FeaturesADTR.CategoriesVH>() {


    class CategoriesVH(mBinding: FeatureListItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        val binding: FeatureListItemBinding = mBinding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        return CategoriesVH(
            FeatureListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: CategoriesVH, position: Int) {
        val categoriesList = ArrayList(movie.filter { it.movieHomeListType == Movie.MovieHomeListType.FEATURED.name })
        val movieCategory = categoriesList[position]
        holder.binding.titleTV.text = movieCategory.title
        holder.binding.yearTV.text = movieCategory.year
        if (movieCategory.poster.isNotEmpty()) {
            Picasso.get().load(movieCategory.poster).into(holder.binding.movieIV)
        }
    }
}