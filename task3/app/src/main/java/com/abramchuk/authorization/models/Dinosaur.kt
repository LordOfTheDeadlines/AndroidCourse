package com.abramchuk.authorization.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dinosaur")
data class Dinosaur(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "length") val length: Double,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}