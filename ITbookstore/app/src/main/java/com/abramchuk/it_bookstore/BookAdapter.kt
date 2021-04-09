package com.abramchuk.it_bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abramchuk.it_bookstore.models.Book

class BookAdapter(var cellClickListener: BookClickListener) : RecyclerView.Adapter<BookAdapter.BookHolder>() {
    var list = listOf<Book>()

    fun update(list: List<Book>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder:BookHolder, position: Int) {
        holder.bind(list[position],cellClickListener)
    }

    class BookHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Book, action: BookClickListener) {
            view.setOnClickListener {
                action.onCellClickListener(item, adapterPosition)
            }

            val title = view.findViewById<TextView>(R.id.book_title)
            title.setOnClickListener {  }
            title.text = item.title
        }

    }
}
