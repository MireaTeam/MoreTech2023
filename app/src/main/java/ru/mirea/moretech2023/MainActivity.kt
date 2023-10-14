package ru.mirea.moretech2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import ru.mirea.moretech2023.ui.screens.officechoice.YandexMap

class MainActivity : ComponentActivity(), UserLocationObjectListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = applicationContext
        val mapView = MapView(context)

        setContent {
            //WelcomeScreen()
            YandexMap(mapView)
        }
    }

    override fun onObjectAdded(p0: UserLocationView) {
        TODO("Not yet implemented")
    }

    override fun onObjectRemoved(p0: UserLocationView) {
        TODO("Not yet implemented")
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
        TODO("Not yet implemented")
    }
}