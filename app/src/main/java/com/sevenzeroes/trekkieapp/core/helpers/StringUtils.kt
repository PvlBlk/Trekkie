package com.sevenzeroes.trekkieapp.core.helpers

fun String?.isValidSearchQuery() : Boolean =
    this != null && this.trim().isNotEmpty()