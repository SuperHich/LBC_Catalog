package com.superhapp.lbccatalog.ui.model

import java.io.Serializable

data class Book(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Serializable