package com.abramchuk.it_bookstore.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abramchuk.it_bookstore.BookAdapter
import com.abramchuk.it_bookstore.BookClickListener
import com.abramchuk.it_bookstore.R
import com.abramchuk.it_bookstore.models.Book
import com.abramchuk.it_bookstore.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksFragment : Fragment(), BookClickListener {
    var navController: NavController?=null
    private lateinit var titleFragment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleFragment = requireArguments().getString("title_fragment").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        GlobalScope.launch(Dispatchers.IO) {
            val listResponse = ApiService.instance().searchBooks(titleFragment,"1")

            withContext(Dispatchers.Main) {
                val books = listResponse.body()!!.books
                Log.d("TEST_RESP", books.toString())
                val rv = view.findViewById<RecyclerView>(R.id.listRv)
                rv.layoutManager = LinearLayoutManager(context)
                val adapter = BookAdapter(this@BooksFragment)
                rv.adapter = adapter
                adapter.update(books)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onCellClickListener(item: Book, position: Int) {
        val bundle = bundleOf("book_id" to item.toString())
        navController!!.navigate(
            R.id.action_booksFragment_to_bookInfoFragment,
            bundle
        )
    }
}