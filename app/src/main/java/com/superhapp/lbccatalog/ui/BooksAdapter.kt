package com.superhapp.lbccatalog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.superhapp.lbccatalog.R
import com.superhapp.lbccatalog.ui.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(private var books: List<Book>)
    : RecyclerView.Adapter<BooksAdapter.BViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BViewHolder(view)
    }

    override fun onBindViewHolder(vh: BViewHolder, position: Int) {
        vh.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun update(data: List<Book>) {
        books = data
        notifyDataSetChanged()
    }

    class BViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.textViewTitle
        private val imageView: ImageView = view.imageView
        fun bind(book: Book) {
            textViewName.text = itemView.context.getString(R.string.title_placeholder, book.id, book.title)
            Picasso.get().load(book.thumbnailUrl).into(imageView)
        }
    }
}