package com.sevenzeroes.trekkieapp.list.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.Glide
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.databinding.EpisodeRowBinding
import com.sevenzeroes.trekkieapp.list.data.EpisodeSummary


class EpisodesRecyclerViewAdapter(
    private val values: List<EpisodeSummary>,
    private val context: Context
) : RecyclerView.Adapter<EpisodesRecyclerViewAdapter.ViewHolder>() {

    companion object{
        const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EpisodeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val binding = holder.binding

        binding.tvTitle.text = item.name
        binding.tvEpisodeSummary.text = item.overview
        binding.tvAirDate.text = item.air_date
        binding.tvRating.text = item.vote_average.toString()
        binding.tvRating.setTextColor(getColor(context, pickColor(item.vote_average)))

        val seasonEpisodePlaceholder = "s0" + item.season_number + "e" + item.episode_number
        binding.tvSeasonEpisode.text = seasonEpisodePlaceholder

        Glide.with(context).load(IMAGES_BASE_URL+item.still_path).centerCrop().into(binding.ivStill)

        val isExpanded : Boolean? = item.expanded
        binding.clExpandable.visibility = if (isExpanded!!) View.VISIBLE else View.GONE

        binding.clEpisodeTop.setOnClickListener{
            item.expanded = !item.expanded!!
            notifyItemChanged(position)
        }
    }

    private fun pickColor(vote : Double?) : Int {
        val voteInt : Int = vote?.toInt()!!
        val color : Int =
        when (voteInt) {
            in 0..4 -> R.color.rating_red
            in 4..6 -> R.color.rating_yellow
            else -> {
                R.color.rating_green
            }
        }
        return color
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: EpisodeRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}