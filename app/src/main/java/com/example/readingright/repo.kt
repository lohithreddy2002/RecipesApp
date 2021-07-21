package com.example.readingright

import androidx.lifecycle.LiveData
import com.example.readingright.db.Meal
import io.ktor.client.request.*

class Repo(private val a: RecipieDatabase) {
    suspend fun getrandom(): ingredient =
        ktorclient.client.get("https://www.themealdb.com/api/json/v1/1/random.php")

    suspend fun search(name: String): ingredient =
        ktorclient.client.get("https://www.themealdb.com/api/json/v1/1/search.php") {
            parameter("s", name)
        }

    suspend fun insert(item: Meal) = a.getdao().insert(item)

    fun getsaved(): LiveData<List<Meal>> = a.getdao().getall()
}