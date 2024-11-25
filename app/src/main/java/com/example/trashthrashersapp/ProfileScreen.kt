package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This is where the profile info goes, just contains text at the moment
 */
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            "Temporary Profile Screen"
        )
    }
}