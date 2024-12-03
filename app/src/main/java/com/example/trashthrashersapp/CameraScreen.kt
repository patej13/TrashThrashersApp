package com.example.trashthrashersapp

import android.graphics.Camera
import android.widget.Button
import android.widget.ImageView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This is where camera code will go
 */

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            "Temporary Camera Screen"
        )
    }
}

