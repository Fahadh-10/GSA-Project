package com.example.gsaprojectassessment.movieVH

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gsaprojectassessment.adapter.CategoriesADTR
import com.example.gsaprojectassessment.databinding.CategoryListBinding
import com.example.gsaprojectassessment.model.Movie

class CategoriesViewHolder(private val binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var mAdapter : CategoriesADTR

    companion object {
        fun getViewHolder(parent: ViewGroup) : CategoriesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CategoryListBinding.inflate(layoutInflater, parent, false)
            return CategoriesViewHolder(binding)
        }
    }

    fun configureCell(movie: ArrayList<Movie>?, context: Context) {
        binding.categoriesRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
        mAdapter = CategoriesADTR(movie ?: ArrayList())
        binding.categoriesRV.adapter = mAdapter
    }
}