package com.example.androidcovid19caloriestracker.network.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidcovid19caloriestracker.model.FoodModel

@Database(entities = [FoodModel::class], version = 3, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract val foodDatabaseDao: FoodDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}