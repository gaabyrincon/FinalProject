package com.agrv.finalproject.view.components.sneakerwizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agrv.finalproject.R
import com.agrv.finalproject.enumerators.ViewIDs
import com.agrv.finalproject.view.components.CustomSpacer
import com.agrv.finalproject.viewmodel.SneakerStoreViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable

fun FinishScreen (navController: NavController,
                  sneakerStoreViewModel: SneakerStoreViewModel){

    Column ( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            val lottieComposition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.loadingsneaker)
            )

            LottieAnimation(
                composition = lottieComposition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(400.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center)
            )
        }

        Text(
            text = stringResource(R.string.thanks),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        CustomSpacer(height = 8.dp)

        Button (
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            onClick = {
                sneakerStoreViewModel.reset()
                navController.navigate(ViewIDs.Start.id)
            }
        ) {
            Text(text = stringResource(R.string.start_over))
        }
    }



}