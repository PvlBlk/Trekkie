package com.sevenzeroes.trekkieapp.list.data

import androidx.recyclerview.widget.DiffUtil

class EpisodeDiffUtilCallback(var oldList: List<EpisodeSummary>, var newList: List<EpisodeSummary>): DiffUtil.Callback() {

    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].still_path == newList[newItemPosition].still_path
    }
}