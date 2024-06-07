package com.example.gsaprojectassessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsaprojectassessment.databinding.CategoryListItemBinding
import com.example.gsaprojectassessment.model.Movie
import com.squareup.picasso.Picasso

class CategoriesADTR(var movie: ArrayList<Movie>) : RecyclerView.Adapter<CategoriesADTR.CategoriesVH>() {

    class CategoriesVH(mBinding: CategoryListItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        val binding: CategoryListItemBinding = mBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        return CategoriesVH(
            CategoryListItemBinding.inflate(
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
        val movieCategory = movie[position]
        holder.binding.titleTV.text = movieCategory.title
        holder.binding.yearTV.text = movieCategory.year
        if (movieCategory.poster.isNotEmpty()) {
            Picasso.get().load(movieCategory.poster).into(holder.binding.movieIV)
        }
    }
}