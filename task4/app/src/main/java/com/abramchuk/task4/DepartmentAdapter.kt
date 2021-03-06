package com.abramchuk.task4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DepartmentAdapter(var cellClickListener: DepartmentClickListener) : RecyclerView.Adapter<DepartmentAdapter.DepartmentHolder>() {
    var list = listOf<Department>()

    fun update(list: List<Department>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.department_item, parent, false)
        return DepartmentHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder:DepartmentHolder, position: Int) {
        holder.bind(list[position],cellClickListener)
    }

    class DepartmentHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Department, action: DepartmentClickListener) {
            view.setOnClickListener {
                action.onCellClickListener(item, adapterPosition)
            }

            val title = view.findViewById<TextView>(R.id.department_title)
            title.setOnClickListener {  }
            title.text = item.name
        }

    }
}
