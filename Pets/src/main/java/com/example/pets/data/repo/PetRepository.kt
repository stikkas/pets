package com.example.pets.data.repo

import com.example.pets.data.model.Cat
import com.example.pets.data.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    fun getPets(): List<Pet>

    suspend fun getCats(): Flow<List<Cat>>

    suspend fun fetchRemoteCats()

    suspend fun update(cat: Cat)

    suspend fun getFavorites(): Flow<List<Cat>>
}