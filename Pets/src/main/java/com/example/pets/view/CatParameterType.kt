package com.example.pets.view

import android.os.Bundle
import androidx.navigation.NavType
import com.example.pets.data.model.Cat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal val CatParameterType = object : NavType<Cat>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Cat? {
        return bundle.getString(key)?.let { parseValue(it) }
    }

    override fun put(bundle: Bundle, key: String, value: Cat) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun parseValue(value: String): Cat = Json.decodeFromString(value)

    override fun serializeAsValue(value: Cat): String = Json.encodeToString(value)
}