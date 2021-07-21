package com.example.readingright

import com.example.readingright.db.Meal
import kotlinx.serialization.Serializable

@Serializable
data class ingredient(
    val meals: List<Meal>?
)