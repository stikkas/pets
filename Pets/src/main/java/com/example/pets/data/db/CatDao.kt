package com.example.pets.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: CatEntity)

    @Query("select * from Cat")
    fun getCats(): Flow<List<CatEntity>>

    @Update
    suspend fun update(cat: CatEntity)

    @Query("select * from Cat where isFavorite = 1")
    fun getFavoriteCats(): Flow<List<CatEntity>>
}