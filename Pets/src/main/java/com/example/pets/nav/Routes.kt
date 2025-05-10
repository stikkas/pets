package com.example.pets.nav

import com.example.pets.data.model.Cat
import kotlinx.serialization.Serializable

@Serializable
object PetsRoute

@Serializable
data class PetDetailsRoute(val cat: Cat)