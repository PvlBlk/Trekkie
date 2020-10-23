package com.example.trekkieapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.trekkieapp.model.EpisodeSummary
import com.example.trekkieapp.network.NetworkService

/**
 * [RecyclerView.Adapter]
 */
class MyEpisodeRecyclerViewAdapter(
    private val values: List<EpisodeSummary>
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
/*
        NetworkService.picasso.load("https://image.tmdb.org/t/p/w500"+item.still_path).into(holder.still)
*/
        holder.airDate.text = item.air_date
/*
        holder.ratingBar.rating = (item.vote_average!! / 10 * 5 ).toFloat() //todo
*/
        holder.title.text = item.name
        var isExpanded : Boolean? = item.expanded
        holder.expandableLayout.visibility = if (isExpanded!!) View.VISIBLE else View.GONE
        holder.title.setOnClickListener{
            item.expanded = !item.expanded!!
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

       // val idView: TextView = view.findViewById(R.id.item_number)
        val expandableLayout : ConstraintLayout = view.findViewById(R.id.expandableLayout)
        val contentView: TextView = view.findViewById(R.id.content)
        /*
        val still: ImageView = view.findViewById(R.id.still)
*/
        val airDate: TextView = view.findViewById(R.id.airDateTextView)
/*
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
*/
        var title: TextView = view.findViewById(R.id.title)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
//TODO
    }
}