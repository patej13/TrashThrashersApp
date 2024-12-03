package com.example.trashthrashersapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

@Composable
fun MapScreen() {
    val cameraPositionState = rememberCameraPositionState {
        // Set the camera's initial position (replace with your campus location)
        position = CameraPosition.fromLatLngZoom(LatLng(40.748817, -73.985428), 16f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
}