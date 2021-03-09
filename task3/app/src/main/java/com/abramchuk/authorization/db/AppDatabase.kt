package com.abramchuk.authorization.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abramchuk.authorization.models.Dinosaur

@Database(entities = [Dinosaur::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDinosaurDAO(): DinosaurDAO
    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "test").build()

    }
}