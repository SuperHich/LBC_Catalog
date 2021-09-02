package com.superhapp.lbccatalog.ui.model

import com.superhapp.lbccatalog.data.model.Book as DataBook
import com.superhapp.lbccatalog.ui.model.Book as ViewBook

fun transform(dataBooks: List<DataBook>) : List<ViewBook> {
    return dataBooks.map {
        it.toViewModel()
    }
}