package com.example.pets.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("id")
    val id: String,
    @SerialName("owner")
    val owner: String = "",
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("updatedAt")
    val updatedAt: String = ""
)
