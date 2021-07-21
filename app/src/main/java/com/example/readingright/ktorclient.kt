package com.example.readingright

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json

object ktorclient {
    private val json = Json { coerceInputValues = true }

    val client = HttpClient(Android) {
        engine {
            connectTimeout = 100_000
        }
        install(Logging)
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

    }

}