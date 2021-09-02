package com.superhapp.lbccatalog.ui.model

import com.superhapp.lbccatalog.data.model.Book as ModelBook

fun ModelBook.toViewModel() : Book {

    return Book(
        this.albumId,
        this.id,
        this.title,
        this.url,
        this.thumbnailUrl
    )
}