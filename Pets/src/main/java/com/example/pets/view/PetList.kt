package com.example.pets.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pets.data.model.Cat
import com.example.pets.viewmodel.PetViewModel

@Composable
fun PetList(
    modifier: Modifier = Modifier, onPetClicked: (Cat) -> Unit
) {
    val model = hiltViewModel<PetViewModel>()
//    val pets = remember { model.getPets().sortedBy { it.species + it.name } }
    val catsState by model.catsState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = catsState.isLoading) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(visible = catsState.cats.isNotEmpty()) {
            LazyColumn {
                items(catsState.cats) {
                    PetListItem(it, onPetClicked) { model.updatePet(it) }
                }
            }
        }
        AnimatedVisibility(visible = catsState.error != null) {
            Text(text = catsState.error ?: "")
        }
    }
}
