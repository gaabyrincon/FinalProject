package com.agrv.finalproject.view.components.sneakerwizard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agrv.finalproject.R
import com.agrv.finalproject.enumerators.ViewIDs
import com.agrv.finalproject.viewmodel.SneakerStoreViewModel


private fun ShareOrder(context: Context, title: String, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            title
        )
    )

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun OrderSummaryScreen (navController: NavController,
                        sneakerStoreViewModel: SneakerStoreViewModel){
    val context = LocalContext.current

    val summary = sneakerStoreViewModel.state.quantity.toString() +
            " " + stringResource(R.string.sneaker_tag) + "\n" +
            " " + stringResource(R.string.selected_color) + sneakerStoreViewModel.state.color + "\n"


    val title = stringResource(R.string.newOrder)
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(stringResource(R.string.quantity).uppercase())
            Text(
                text = sneakerStoreViewModel.state.quantity.toString() +
                    " " + stringResource(R.string.sneaker_tag),
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(thickness = 2.dp)
            Text(stringResource(R.string.selected_color).uppercase())
            Text(
                text = sneakerStoreViewModel.state.color,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(thickness = 2.dp)
            Text(
                text = stringResource(R.string.subtotal,
                    sneakerStoreViewModel.state.total),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Row (modifier = Modifier.padding(16.dp)){
            Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        ShareOrder(context,title,"Nueva Orden", summary)
                    }
                ) {
                    Text(stringResource(R.string.share_info))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(ViewIDs.FinishOrder.id)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )
                ) {
                    Text(stringResource(R.string.send_order))
                }
            }
        }
    }
}