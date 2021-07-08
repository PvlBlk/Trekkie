package com.sevenzeroes.trekkieapp.core.helpers

fun String?.isValid() : Boolean =
    this != null && this.trim().isNotEmpty()