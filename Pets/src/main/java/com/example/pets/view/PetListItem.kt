package com.example.pets.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pets.data.model.Cat


@Composable
fun PetListItem(
    cat: Cat, onClick: (Cat) -> Unit,
    onFavoriteClicked: (Cat) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable {
                    onClick(cat)
                }
        ) {
            AsyncImage(
                model = "https://cataas.com/cat/${cat.id}",
                contentDescription = "Cute cat",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
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
}

@Composable
fun TagsRow(cat: Cat, isFavorite: Boolean, onFavoriteClicked: (Cat) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FlowRow(modifier = Modifier.padding(start = 6.dp, end = 6.dp)) {
            repeat(cat.tags.size) {
                SuggestionChip(
                    modifier = Modifier.padding(start = 3.dp, end = 3.dp), onClick = {},
                    label = { Text(text = cat.tags[it]) })
            }
        }
        Icon(
            modifier = Modifier.clickable { onFavoriteClicked(cat.copy(isFavorite = !cat.isFavorite)) },
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}

@Preview
@Composable
fun TagsRowPreview() {
    TagsRow(
        Cat(createdAt = "", updatedAt = "", id = "", owner = "", tags = listOf("Tag1", "Tag2")),
        false, {},
        Modifier
            .padding(start = 6.dp, end = 6.dp)
            .fillMaxWidth()
    )
}