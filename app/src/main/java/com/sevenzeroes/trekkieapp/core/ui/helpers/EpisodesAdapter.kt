package com.sevenzeroes.trekkieapp.core.ui.helpers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.sevenzeroes.trekkieapp.R
import com.sevenzeroes.trekkieapp.databinding.EpisodeItemBinding
import com.sevenzeroes.trekkieapp.core.data.EpisodeDiffUtilCallback
import com.sevenzeroes.trekkieapp.core.domain.models.EpisodeSummary
import com.sevenzeroes.trekkieapp.core.helpers.getId
import com.sevenzeroes.trekkieapp.core.helpers.setImage


class EpisodesAdapter(private val toggleFavourite: ToggleFavourite, private var isExpanded: Boolean) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    private var episodes = mutableListOf<EpisodeSummary>()

    companion object{
        const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500"
        val LOW_RATING_RANGE = 0..4
        val MEDIUM_RATING_RANGE = 4..6
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        val binding = holder.binding
        val isExpanded : Boolean? = episode.expanded
        val episodeId = episode.getId()

        binding.apply {
            tvTitle.text = episode.name
            tvEpisodeSummary.text = episode.overview
            tvAirDate.text = episode.air_date
            tvRating.text = episode.vote_average.toString()
            tvSeasonEpisode.text = episodeId
            val favoriteIcon = if (episode.isFavorite == true) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_empty
            ivFavorite.setImage(favoriteIcon) //todo при поиске подставлять корректный значок избранного

            clExpandable.visibility = if (isExpanded!!) View.VISIBLE else View.GONE
            clEpisodeTop.setOnClickListener{
                expand(episode)
                notifyItemChanged(position)
            }
            ivFavorite.setOnClickListener {
                toggleFavourite.onToggleFavorite(episode)
            }
            tvRating.setTextColor(getColor(binding.root.context, pickRatingColor(episode.vote_average)))
        }


        Glide.with(binding.ivStill.context).load(IMAGES_BASE_URL +episode.still_path).centerCrop().into(binding.ivStill)


    }

    private fun expand(episode: EpisodeSummary) {
        episode.expanded = !episode.expanded!!
    }

    private fun pickRatingColor(vote : Double?) : Int {
        return when (vote?.toInt()) {
            in LOW_RATING_RANGE -> R.color.rating_red
            in MEDIUM_RATING_RANGE -> R.color.rating_yellow
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
        episodes.clear()
        episodes.addAll(newEpisodes)
        diffResult.dispatchUpdatesTo(this)
    }
}

