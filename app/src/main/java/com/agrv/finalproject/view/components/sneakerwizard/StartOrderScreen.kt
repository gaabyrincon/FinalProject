package com.agrv.finalproject.view.components.sneakerwizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agrv.finalproject.R
import com.agrv.finalproject.enumerators.ViewIDs
import com.agrv.finalproject.enumerators.ViewModelIDs
import com.agrv.finalproject.model.SneakerOrderState
import com.agrv.finalproject.staticdata.DataSource
import com.agrv.finalproject.view.components.CustomSpacer
import com.agrv.finalproject.view.components.TitleMedium
import com.agrv.finalproject.viewmodel.SneakerStoreViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun StartOrderScreen (navController : NavController,
                      sneakerStoreViewModel: SneakerStoreViewModel){

    var quantityOptions = DataSource.quantityOptions

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            CustomSpacer(height = 100.dp)
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.sneaker1)
            )

            LottieAnimation(
                composition=composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxWidth()

            )
            CustomSpacer(height = 16.dp)
            TitleMedium(stringResource(R.string.ordenar_sneakers))

            CustomSpacer(height = 8.dp)
            TitleMedium(stringResource(R.string.sneaker_price,sneakerStoreViewModel.state.price))

            CustomSpacer(height = 8.dp)
        }

        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            quantityOptions.forEach{option ->
                Button(
                    onClick = {
                        sneakerStoreViewModel.onValue(
                            option.second.toString(),
                            ViewModelIDs.Quantity.id
                        )
                        navController.navigate(ViewIDs.Details.id)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(text = option.second.toString()+" "+ stringResource(option.first))
                }
            }
        }
    }

}