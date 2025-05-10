package com.example.pets.di

import android.content.Context
import androidx.room.Room
import com.example.pets.data.db.CatDao
import com.example.pets.data.db.CatDatabase
import com.example.pets.data.network.CatsApi
import com.example.pets.data.repo.PetRepository
import com.example.pets.data.repo.PetRepositoryImpl
import com.example.pets.viewmodel.PetViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import javax.inject.Singleton

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

/**
 * isn't used now,
 * instead this 'hilt' is used
 */
val appModule = module {
//    single<PetRepository> { PetRepositoryImpl() }
    single { PetViewModel(get()) }
}

@Module
@InstallIn(SingletonComponent::class)
object PetsModule {

    @Provides
    @Singleton
    fun bindPetRepository(catsApi: CatsApi, catDao: CatDao): PetRepository =
        PetRepositoryImpl(catsApi, Dispatchers.IO, catDao)

    @Provides
    fun provideBaseUrl(): String = "https://cataas.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory(contentType = "application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CatsApi = retrofit.create(CatsApi::class.java)

    @Provides
    @Singleton
    fun catDatabase(@ApplicationContext ctx: Context): CatDatabase =
        Room.databaseBuilder(ctx, CatDatabase::class.java, "cat-database").build()

    @Provides
    @Singleton
    fun catDao(db: CatDatabase): CatDao = db.catDao()
}
