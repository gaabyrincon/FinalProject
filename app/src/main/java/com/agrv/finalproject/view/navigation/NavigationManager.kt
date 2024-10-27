package com.agrv.finalproject.view.navigation

import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.agrv.finalproject.enumerators.ViewIDs
import com.agrv.finalproject.view.components.onboarding.MainOnboarding
import com.agrv.finalproject.view.components.sneakerwizard.FinishScreen
import com.agrv.finalproject.view.components.sneakerwizard.OrderSummaryScreen
import com.agrv.finalproject.view.components.sneakerwizard.SelectDetailsScreen
import com.agrv.finalproject.view.components.sneakerwizard.StartOrderScreen
import com.agrv.finalproject.view.components.splash.SplashScreen
import com.agrv.finalproject.viewmodel.SneakerStoreViewModel
import org.w3c.dom.Text as Text

//TODO: TOPBAR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerAppBar (currentScreen: ViewIDs) {
    CenterAlignedTopAppBar(
        title = { Text(currentScreen.tag) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}


@Composable
fun NavigationManager (SneakerStoreViewModel: SneakerStoreViewModel) {

    val navController = rememberNavController();

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ViewIDs.valueOf(
        backStackEntry?.destination?.route?: ViewIDs.Start.id
    )

    Scaffold ( //andamiaje que necesita nuestra vida para empezar a integrar componentes y le da orden
        topBar = {
            if (currentScreen !in listOf(ViewIDs.Splash, ViewIDs.Home, ViewIDs.FinishOrder)) {
                SneakerAppBar(currentScreen)
            }
        },
        bottomBar = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Sneaker Store")
            }

        }
    ) {
        NavHost(
            navController = navController,
            startDestination = ViewIDs.Splash.id, //primer pantalla que vamos a ver
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ){
            composable (ViewIDs.Splash.id) {
               SplashScreen(navController)
            }

            composable( ViewIDs.Home.id) {
                MainOnboarding(navController)

            }
            composable (ViewIDs.Start.id){
                StartOrderScreen(navController,SneakerStoreViewModel)
            }
            composable(ViewIDs.Details.id) {
               SelectDetailsScreen(navController, SneakerStoreViewModel)
            }


            composable (ViewIDs.OrderSummary.id) {
               OrderSummaryScreen(navController, SneakerStoreViewModel)
            }
           composable(ViewIDs.FinishOrder.id) {
                FinishScreen(navController,SneakerStoreViewModel)
            }
        }
   }
}




//}