package com.example.trashthrashersapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@Composable
fun WhiteBackground(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        content()
    }
}
@Composable
fun TwoColorLoginBackground() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDFFF5), // Light Mint (top section)
                        Color(0xFFFFB347)  // Orange (bottom section)
                    )
                )
            )
    ) {



        }
    }
@Composable
fun TwoColorProfileBackground() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDFFF5), // Light Mint (top section)
                        Color(0xFF00BCD4)  // Teal (bottom section)
                    )
                )
            )
    ){
}

}
@Composable
fun TwoColorSignUpBackground() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDFFF5), // Light Mint (top section)
                        Color(0xFFFF6347)  // Red (bottom section)
                    )
                )
            )
    ) {

    }

}
