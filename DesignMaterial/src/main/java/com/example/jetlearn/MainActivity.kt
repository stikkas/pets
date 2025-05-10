package com.example.jetlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.jetlearn.materialcomponents.PacktBottomAppBar
import com.example.jetlearn.materialcomponents.PacktExtendedFloatingActionButton
import com.example.jetlearn.materialcomponents.PacktFloatingActionButton
import com.example.jetlearn.materialcomponents.PacktSmallTopAppBar
import com.example.jetlearn.ui.theme.MyTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyTheme {
//                when(calculateWindowSizeClass(this).widthSizeClass) {
//                    WindowWidthSizeClass.Compact -> CharactersScreen
//                        WindowWidthSizeClass.Medium ->
//                    WindowWidthSizeClass.Expanded ->
//                        else ->
//                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { PacktSmallTopAppBar() },
                    floatingActionButton = {
                        Row(horizontalArrangement = Arrangement.SpaceAround) {
                            PacktFloatingActionButton()
                            PacktExtendedFloatingActionButton()
                        }
                    },
                    bottomBar = { PacktBottomAppBar() }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = .1f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}
