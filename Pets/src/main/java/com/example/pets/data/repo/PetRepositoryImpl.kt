package com.example.pets.data.repo

import android.util.Log
import com.example.pets.data.db.CatDao
import com.example.pets.data.db.CatEntity
import com.example.pets.data.model.Cat
import com.example.pets.data.model.Pet
import com.example.pets.data.network.CatsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlin.random.Random

class PetRepositoryImpl(
    private val catsApi: CatsApi,
    private val dispatcher: CoroutineDispatcher,
    private val catDao: CatDao
) : PetRepository {
    init {
        Log.i("HILT", "create PetRepository")
    }

    private val names = listOf(
        "Bella",
        "Luna",
        "Charlie",
        "Lucy",
        "Cooper",
        "Max",
        "Bailey",
        "Daisy",
        "Sadie",
        "Lily",
        "Jueelie",
        "Memmie",
        "Aruno",
        "George",
        "Zimbava"
    )

    private val species =
        listOf("Cat", "Dog", "Rabbit", "Turtle", "Fish", "Hare", "Wolf", "Hamster", "Pig")

    override fun getPets(): List<Pet> {
        return List(150) {
            Pet(
                it + 1,
                names[Random.nextInt(0, names.size)],
                species[Random.nextInt(0, species.size)]
            )
        }
    }

    override suspend fun getCats(): Flow<List<Cat>> = withContext(dispatcher) {
        catDao.getCats().map {
            it.map {
                Cat(
                    id = it.id,
                    owner = it.owner,
                    tags = it.tags,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    isFavorite = it.isFavorite
                )
            }
        }.onEach {
            if (it.isEmpty()) {
                fetchRemoteCats()
            }
        }
    }

    override suspend fun fetchRemoteCats() {
        withContext(dispatcher) {
            val response = catsApi.fetchCats("cute")
            if (response.isSuccessful) {
                response.body()?.forEach {
                    catDao.insert(
                        CatEntity(
                            id = it.id,
                            owner = it.owner,
                            tags = it.tags,
                            createdAt = it.createdAt,
                            updatedAt = it.updatedAt,
                            isFavorite = it.isFavorite
                        )
                    )
                }
            }
        }
    }

    override suspend fun update(cat: Cat) {
        withContext(dispatcher) {
            catDao.update(
                CatEntity(
                    id = cat.id,
                    owner = cat.owner,
                    tags = cat.tags,
                    createdAt = cat.createdAt,
                    updatedAt = cat.updatedAt,
                    isFavorite = cat.isFavorite
                )
            )
        }
    }

    override suspend fun getFavorites(): Flow<List<Cat>> {
        return withContext(dispatcher) {
            catDao.getFavoriteCats().map {
                it.map {
                    Cat(
                        id = it.id,
                        owner = it.owner,
                        tags = it.tags,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt,
                        isFavorite = it.isFavorite
                    )
                }
            }
        }
    }
}