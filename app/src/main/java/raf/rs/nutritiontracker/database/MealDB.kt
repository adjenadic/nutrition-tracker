package raf.rs.nutritiontracker.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MealDBEntity::class],
    version = 4,
    exportSchema = false
)
abstract class MealDB : RoomDatabase() {
    abstract fun getMealDao(): MealDBDao
}