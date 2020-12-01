package com.sevenzeroes.trekkieapp

import android.content.Context
import android.graphics.Color.red
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.Glide
import com.sevenzeroes.trekkieapp.model.EpisodeSummary

/**
 * [RecyclerView.Adapter]
 */
class MyEpisodeRecyclerViewAdapter(
    private val values: List<EpisodeSummary>,
private val context: Context
) : RecyclerView.Adapter<MyEpisodeRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        // holder.idView.text = item.air_date
        holder.contentView.text = item.overview
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+item.still_path).centerCrop().into(holder.still)
        holder.airDate.text = item.air_date
        holder.rating.text = item.vote_average.toString()
        holder.rating.setTextColor(getColor(context, pickColor(item.vote_average)))
        holder.title.text = item.name
        var seasonEpisodePlaceholder = "s0" + item.season_number + "e" + item.episode_number
        holder.seasonEpisode.text = seasonEpisodePlaceholder
        var isExpanded : Boolean? = item.expanded
        holder.expandableLayout.visibility = if (isExpanded!!) View.VISIBLE else View.GONE
        holder.headLayout.setOnClickListener{
            item.expanded = !item.expanded!!
            notifyItemChanged(position)
        }
    }

    private fun pickColor(vote : Double?) : Int {
        var voteInt : Int = vote?.toInt()!!
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

       // val idView: TextView = view.findViewById(R.id.item_number)
        val expandableLayout : ConstraintLayout = view.findViewById(R.id.expandableLayout)
        val headLayout : ConstraintLayout = view.findViewById(R.id.head)
        val contentView: TextView = view.findViewById(R.id.content)

        val still: ImageView = view.findViewById(R.id.still)
        val seasonEpisode: TextView = view.findViewById(R.id.season_episode)
        val airDate: TextView = view.findViewById(R.id.air_date)
        val rating: TextView = view.findViewById(R.id.rating)
        var title: TextView = view.findViewById(R.id.title)
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}