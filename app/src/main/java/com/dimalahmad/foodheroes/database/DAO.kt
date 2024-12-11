package com.dimalahmad.foodheroes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDonationDao {
    @Insert
    fun insertFavorite(donation: FavoriteDonation)

    @Query("SELECT * FROM FavoriteDonation")
    fun getFavorites(): List<FavoriteDonation>
}