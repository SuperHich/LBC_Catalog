package com.superhapp.lbccatalog.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "book")
data class Book(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "album_id")
    val albumId: Int,
    val title: String,
    val url: String,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String
)
