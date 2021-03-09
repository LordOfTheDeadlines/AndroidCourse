package com.abramchuk.authorization.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abramchuk.authorization.models.Dinosaur

@Dao
interface DinosaurDAO {
    @Insert
    fun insert(dino: Dinosaur)

    @Query("SELECT * FROM dinosaur")
    fun getDinosaurs() : List<Dinosaur>

    @Query("DELETE FROM dinosaur")
    fun deleteAll()
}