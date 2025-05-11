package com.example.pets.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pets.data.model.Cat
import com.example.pets.view.CatParameterType
import com.example.pets.view.PetDetailsScreen
import com.example.pets.view.PetsScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, PetsRoute) {
        composable<PetsRoute> {
            PetsScreen(onPetClicked = { cat -> navController.navigate(PetDetailsRoute(cat)) })
        }
        composable<PetDetailsRoute>(typeMap = mapOf(typeOf<Cat>() to CatParameterType)) { entity ->
            val cat = entity.toRoute<PetDetailsRoute>().cat
            PetDetailsScreen(cat) {
                navController.popBackStack()
            }
        }
    }
}
