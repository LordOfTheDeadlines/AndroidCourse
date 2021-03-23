package com.abramchuk.task4

interface DepartmentClickListener {
    fun onCellClickListener(item: Department, position: Int)
}

interface ArtObjectClickListener {
    fun onCellClickListener(item: ArtObject, position: Int)
}