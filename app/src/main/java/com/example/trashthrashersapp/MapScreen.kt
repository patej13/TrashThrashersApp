package com.example.trashthrashersapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

@Composable
fun MapScreen() {
    // Farmingdale State College Center
    val campusCenter = LatLng(40.7528, -73.4264)

    // Camera Position State
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(campusCenter, 16f)
    }

    val markers = remember { mutableStateListOf<LatLng>() }
    val scope = rememberCoroutineScope()

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            markers.add(latLng)
        }
    ) {
        markers.forEach { location ->
            Marker(
                state = rememberMarkerState(position = location),
                title = "User-added Marker",
                snippet = "Lat: ${location.latitude}, Lng: ${location.longitude}",
                onClick = {
                    true 
                }
            )
        }
    }
}
