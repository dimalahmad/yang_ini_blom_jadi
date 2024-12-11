package com.dimalahmad.foodheroes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteDonation(
    @PrimaryKey val id: Int,
    val menuName: String,
    val capacity: Int
)