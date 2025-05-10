package com.example.pets.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets.data.model.Cat
import com.example.pets.data.network.NetworkResult
import com.example.pets.data.network.asResult
import com.example.pets.data.repo.PetRepository
import com.example.pets.view.CatsUIState
import com.example.pets.view.CurrentCat
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PetViewModel @Inject constructor(repo: PetRepository) : ViewModel(), PetRepository by repo {
    private val _curCat =
        MutableSharedFlow<CurrentCat>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val currentCat = _curCat.asSharedFlow()

    val catsState = MutableStateFlow(CatsUIState())
    private val _favoritePets = MutableStateFlow<List<Cat>>(emptyList())
    val favoritePets: StateFlow<List<Cat>>
        get() = _favoritePets

    init {
        resetCurrent()
        catsState.value = CatsUIState(isLoading = true)
        viewModelScope.launch {
            getCats().asResult().collect { result ->
                when (result) {
                    is NetworkResult.Success -> catsState.update {
                        Log.i("RESULT", "Got ${result.data.size} items")
                        it.copy(isLoading = false, cats = result.data)
                    }

                    is NetworkResult.Error -> catsState.update {
                        Log.i("ERROR", "Got ${result.error}")
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun setCurrent(cat: Cat) {
        _curCat.tryEmit(CurrentCat(cat))
    }

    fun resetCurrent() {
        _curCat.tryEmit(CurrentCat())
    }

    fun updatePet(cat: Cat) {
        viewModelScope.launch {
            update(cat)
        }
    }

    fun getFavoritePets() {
        viewModelScope.launch {
            getFavorites().collect {
                _favoritePets.value = it
            }
        }
    }
}
