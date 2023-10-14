package ru.mirea.moretech2023.ui.screens.officechoice

import android.content.Context
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.yandex.mapkit.mapview.MapView


@Composable
fun OfficeChoiceScreen(
    navigateToDetailScreen: () -> Unit,
    mapView: MapView,
    context: Context,
    setLatitude: String?,
    setLongitude: String?,
    chosenTransportationMethod: String?,
    chosenServiceId: String?
) {
    // YandexMap
    // TODO: Add navigation to office detail screen
    Column {
        AndroidView({ mapView }) { view ->

        }

        ExtendedFloatingActionButton(
            onClick = { navigateToDetailScreen },
            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
            text = { Text(text = "Test: jump to details") },
            modifier = Modifier.zIndex(1f)
        )
    }
}

@Preview
@Composable
fun OfficeChoiceScreenPreview() {
    ExtendedFloatingActionButton(
        onClick = {  },
        icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
        text = { Text(text = "Test: jump to details") },
    )
}