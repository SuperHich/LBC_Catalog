package com.superhapp.lbccatalog.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.superhapp.lbccatalog.R
import com.superhapp.lbccatalog.ui.model.Book
import com.superhapp.lbccatalog.ui.viewmodel.BooksViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.layout_error.*
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

class BooksActivity : DaggerAppCompatActivity() {
    companion object {
        const val TAG = "BooksActivity"
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy(NONE) {
        ViewModelProvider(this, factory).get(BooksViewModel::class.java)
    }

    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        setupViewModel()
        setupUI()

        progressBar.visibility = View.VISIBLE
        viewModel.loadBooks()
    }

    private fun setupUI() {
        adapter = BooksAdapter(viewModel.books.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.books.observe(this, handleCatalogBooks)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val handleCatalogBooks = Observer<List<Book>> {
        Log.v(TAG, "data updated $it")
        progressBar.visibility = View.GONE
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        adapter.update(it)
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.v(TAG, "onMessageError $it")
        progressBar.visibility = View.GONE
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }
}