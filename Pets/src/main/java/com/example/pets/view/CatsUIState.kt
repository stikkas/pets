package com.example.pets.view

import com.example.pets.data.model.Cat

data class CatsUIState (
    val isLoading: Boolean = false,
    val cats: List<Cat> = emptyList(),
    val error: String? = null
)