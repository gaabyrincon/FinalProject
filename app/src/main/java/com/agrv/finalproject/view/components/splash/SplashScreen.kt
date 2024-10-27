package com.agrv.finalproject.view.components.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agrv.finalproject.R
import com.agrv.finalproject.enumerators.ViewIDs
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay


@Composable

fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 =true) {
        delay(2000)
        navController.navigate(ViewIDs.Home.id)
    }

    Box(contentAlignment = androidx.compose.ui.Alignment.Center, modifier = Modifier.fillMaxSize()){
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.splashsneaker)
        )

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(500.dp)
                .fillMaxWidth()
                .align(alignment = androidx.compose.ui.Alignment.Center)
        )
    }
}