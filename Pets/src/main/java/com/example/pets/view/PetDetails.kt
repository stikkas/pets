package com.example.pets.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pets.data.model.Cat

@Composable
fun PetDetails(
    cat: Cat,
    onFavoriteClicked: (Cat) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://cataas.com/cat/${cat.id}",
            contentDescription = "Cute cat", modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.FillWidth
        )
        TagsRow(
            cat,
            cat.isFavorite,
            onFavoriteClicked,
            Modifier
                .padding(start = 6.dp, end = 6.dp)
                .fillMaxWidth()
        )
    }
}