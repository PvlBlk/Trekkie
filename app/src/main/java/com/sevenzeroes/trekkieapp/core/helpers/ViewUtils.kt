package com.sevenzeroes.trekkieapp.core.helpers

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat

fun View.hide(){
    this.visibility = View.GONE
}
fun View.show(){
    this.visibility = View.VISIBLE
}

fun ImageView.setImage(favoriteIcon: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(this.context, favoriteIcon))
}
