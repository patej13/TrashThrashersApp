package com.example.trashthrashersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trashthrashersapp.ui.theme.TrashThrashersAppTheme

/**
 * Main, do not alter.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrashThrashersAppTheme {
                val navController: NavHostController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }
                Scaffold(
                    bottomBar = {
                        if (buttonsVisible) {
                            BottomBar(
                                navController = navController,
                                state = buttonsVisible,
                                modifier = Modifier
                            )
                        }
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        NavigationGraph(navController = navController) {
                                isVisible ->
                            buttonsVisible = isVisible
                        }
                    }
                }
            }
        }
    }
}