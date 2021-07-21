package com.example.readingright

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.readingright.db.Meal
import com.example.readingright.db.dao

@Database(
    entities = [Meal::class],
    version = 1
)
abstract class RecipieDatabase : RoomDatabase() {

    abstract fun getdao(): dao

    companion object {
        @Volatile
        private var instance: RecipieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RecipieDatabase::class.java,
            "article_db.db"
        ).fallbackToDestructiveMigration().build()

    }

}
