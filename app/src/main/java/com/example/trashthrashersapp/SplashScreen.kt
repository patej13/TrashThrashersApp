package com.example.trashthrashersapp

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

/**
 * Splash Screen function
 * Contains an animation to show the logo
 * There is currently a temporary logo in place
 * Change the logo when an official one is made
 */
@Composable
fun SplashScreen(navController: NavHostController) {

    val scale= remember {
        Animatable(0f, 1f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000,0, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
            ))
        delay(3000)
        navController.navigate(NavigationItems.Home.route)
    }

    Box (modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)){
        Image(painter = painterResource(id = R.drawable.logo1), contentDescription ="Splash Screen logo" , modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
    }
}