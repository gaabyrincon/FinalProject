package com.agrv.finalproject.view.components.sneakerwizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agrv.finalproject.R
import com.agrv.finalproject.enumerators.ViewIDs
import com.agrv.finalproject.enumerators.ViewModelIDs
import com.agrv.finalproject.staticdata.DataSource
import com.agrv.finalproject.view.components.CustomSpacer
import com.agrv.finalproject.viewmodel.SneakerStoreViewModel

@Composable

fun SelectDetailsScreen (navController: NavController,
                         sneakerStoreViewModel: SneakerStoreViewModel){
    val colors = DataSource.colorsKeys.map { key -> stringResource(key) }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            colors.forEach{ color->
                Row (
                    modifier = Modifier
                        .selectable(
                            selected = sneakerStoreViewModel.state.color == color,
                            onClick = {
                                sneakerStoreViewModel.onValue(color,ViewModelIDs.Color.id)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(
                        selected = sneakerStoreViewModel.state.color == color,
                        onClick = {
                            sneakerStoreViewModel.onValue(color,ViewModelIDs.Color.id)
                        }
                    )
                    Text(color)
                }
            }
            CustomSpacer(height = 16.dp)
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(16.dp)
            )

            Text (
                text = stringResource(R.string.subtotal,
                sneakerStoreViewModel.state.total),
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.Bottom
        ){
            OutlinedButton(
                modifier = Modifier.weight(1F),
                onClick = {
                    sneakerStoreViewModel.reset()
                    navController.popBackStack(
                        ViewIDs.Start.id, false
                    )
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1F),
                onClick = {
                    navController.navigate(ViewIDs.OrderSummary.id)
                },
                enabled = sneakerStoreViewModel.state.color.isNotEmpty()
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }



}