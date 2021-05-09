package com.sevenzeroes.trekkieapp.core.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.databinding.EpisodeItemBinding
import com.sevenzeroes.trekkieapp.core.data.EpisodeDiffUtilCallback
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EpisodesRecyclerViewAdapter(private val episodesViewModel: EpisodesViewModel) : RecyclerView.Adapter<EpisodesRecyclerViewAdapter.ViewHolder>() {

    private var episodes = listOf<EpisodeSummary>()

    companion object{
        const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        val binding = holder.binding
        val isExpanded : Boolean? = episode.expanded
        val seasonEpisodePlaceholder = "s0" + episode.season_number + "e" + episode.episode_number

        binding.apply {
            tvTitle.text = episode.name
            tvEpisodeSummary.text = episode.overview
            tvAirDate.text = episode.air_date
            tvRating.text = episode.vote_average.toString()
            tvSeasonEpisode.text = seasonEpisodePlaceholder

            clExpandable.visibility = if (isExpanded!!) View.VISIBLE else View.GONE
            clEpisodeTop.setOnClickListener{
                episode.expanded = !episode.expanded!!
                notifyItemChanged(position)
            }
            ivFavorite.setOnClickListener {
                GlobalScope.launch {
                    episodesViewModel.interactors?.insert?.invoke(episode)
                }
            }
        }
            //        binding.tvRating.setTextColor(getColor(binding.root.context, pickColor(episode.vote_average)))

        Glide.with(binding.ivStill.context).load(IMAGES_BASE_URL+episode.still_path).centerCrop().into(binding.ivStill)


    }

    private fun pickColor(vote : Double?) : Int {
        return when (vote?.toInt()!!) {
            in 0..4 -> R.color.rating_red
            in 4..6 -> R.color.rating_yellow
            else -> {
                R.color.rating_green
            }
        }
    }

    override fun getItemCount(): Int = episodes.size

    inner class ViewHolder(val binding: EpisodeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    fun setData(newEpisodes: MutableList<EpisodeSummary>){
        val diffUtilCallback = EpisodeDiffUtilCallback(episodes, newEpisodes)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        episodes = newEpisodes
        diffResult.dispatchUpdatesTo(this)
    }
}