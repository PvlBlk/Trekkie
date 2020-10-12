package com.example.trekieapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.trekieapp.model.EpisodeSummary
import com.example.trekieapp.network.NetworkService

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyEpisodeRecyclerViewAdapter(
    private val values: List<EpisodeSummary>
) : RecyclerView.Adapter<MyEpisodeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
       // holder.idView.text = item.air_date
        holder.contentView.text = item.overview
        NetworkService.picasso.load("https://image.tmdb.org/t/p/w500"+item.still_path).into(holder.still)
        holder.airDate.text = item.air_date
        holder.rating.text = item.vote_average.toString()


    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       // val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
        val still: ImageView = view.findViewById(R.id.still)
        val airDate: TextView = view.findViewById(R.id.air_date)
        val rating: TextView = view.findViewById(R.id.rating)
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}