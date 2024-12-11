package com.dimalahmad.foodheroes.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteDonation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDonationDao(): FavoriteDonationDao
}