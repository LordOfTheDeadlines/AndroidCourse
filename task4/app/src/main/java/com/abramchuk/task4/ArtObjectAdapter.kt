package com.abramchuk.task4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtObjectAdapter(var cellClickListener: ArtObjectClickListener) : RecyclerView.Adapter<ArtObjectAdapter.ArtObjectHolder>() {
    var list = listOf<ArtObject>()
    var idList = listOf<Int>()

    fun update(list: List<Int>) {
        this.idList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtObjectHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.department_item, parent, false)
        return ArtObjectHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder:ArtObjectHolder, position: Int) {
        holder.bind(list[position],cellClickListener)
    }

    class ArtObjectHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: ArtObject, action: ArtObjectClickListener) {
            view.setOnClickListener {
                action.onCellClickListener(item, adapterPosition)
            }

            val title = view.findViewById<TextView>(R.id.art_object_title)
            title.setOnClickListener {  }
            title.text = item.title
        }

    }
}