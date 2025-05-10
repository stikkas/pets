package com.example.pets.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pets.data.model.Cat
import com.example.pets.ui.theme.primaryContainerDark


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetsScreen(onPetClicked: (Cat) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Pets") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryContainerDark)
            )
        }
    ) { pad ->
        PetList(
            Modifier
                .fillMaxSize()
                .padding(pad),
            onPetClicked = onPetClicked
        )
    }
}