package com.example.readingright.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Meal)


    @Query("Select * from `Meal`")
    fun getall(): LiveData<List<Meal>>
}