package ru.mirea.moretech2023.ui.screens.officechoice

import android.content.Context
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.mapview.MapView


@Composable
fun OfficeChoiceScreen(
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
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }
}