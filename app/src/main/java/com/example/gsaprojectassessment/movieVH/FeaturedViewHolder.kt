package com.example.gsaprojectassessment.movieVH

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gsaprojectassessment.adapter.FeaturesADTR
import com.example.gsaprojectassessment.databinding.FeaturedListBinding
import com.example.gsaprojectassessment.model.Movie

class FeaturedViewHolder(private val binding: FeaturedListBinding) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var mAdapter : FeaturesADTR

    companion object {
        fun getViewHolder(parent: ViewGroup) : FeaturedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FeaturedListBinding.inflate(layoutInflater, parent, false)
            return FeaturedViewHolder(binding)
        }
    }

    fun configureCell(movie: ArrayList<Movie>, context: Context) {
        binding.categoriesRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
        mAdapter = FeaturesADTR(movie)
        binding.categoriesRV.adapter = mAdapter
        }
    }