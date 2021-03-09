package com.abramchuk.authorization.models

import kotlin.math.roundToInt
import kotlin.random.Random

class DinosaurGroupGenerator {
    private val dinoNames = listOf("Тиранозавр", "Ихтиозавр", "Завзавр", "Велоцираптор", "Птеродактиль")
    private val dinoTypes = listOf("Обыкновенный", "Подкустовный", "Крылатый", "Карликовый", "Огнедышащий")

    fun createGroup(minCount: Int, maxCount: Int): ArrayList<Dinosaur> {
        val groupCount = (minCount..maxCount).random()
        val result = ArrayList<Dinosaur>()
        for(d in 1..groupCount)
            result.add(createDino())
        return result
    }
    private fun createDino():Dinosaur{
        val randomName = dinoNames.random()
        val randomType = dinoTypes.random()
        val randomAge = (0..100).random()
        val randomLength = (Random.nextDouble(0.1, 100.00)*100).roundToInt().toDouble()/100
        val isPredator = Random.nextBoolean()
        return Dinosaur("$randomName $randomType", randomAge, randomLength)
    }
}