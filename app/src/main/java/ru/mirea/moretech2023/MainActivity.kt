package ru.mirea.moretech2023

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouter
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Error
import com.yandex.runtime.ui_view.ViewProvider
import ru.mirea.moretech2023.ui.screens.officechoice.OfficeChoiceScreen
import ru.mirea.moretech2023.ui.screens.officedetails.OfficeDetailsScreen
import ru.mirea.moretech2023.ui.screens.servicechoice.ServiceChoiceScreen
import ru.mirea.moretech2023.ui.screens.welcome.WelcomeScreen
import ru.mirea.moretech2023.ui.theme.MoreTech2023Theme

@Composable
fun MoreTechVtbApp(mapView: MapView, mapViewContext: Context) {
    MoreTech2023Theme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") {
                WelcomeScreen { latitude, longtitude, chosenTransportationMethod ->
                    navController.navigate(
                        "servicechoice" +
                                "/$latitude" +
                                "/$longtitude" +
                                "/$chosenTransportationMethod"
                    )
                }
            }

            composable(
                "servicechoice" +
                        "/{latitude}" +
                        "/{longtitude}" +
                        "/{chosentransportationmethod}"
            ) { backStackEntry ->
                ServiceChoiceScreen(
                    { latitude, longtitude, chosenTransportationMethod, chosenServiceId ->
                        navController.navigate(
                            "officechoice" +
                                    "/$latitude" +
                                    "/$longtitude" +
                                    "/$chosenTransportationMethod" +
                                    "/$chosenServiceId"
                        )
                    },
                    backStackEntry.arguments?.getString("latitude"),
                    backStackEntry.arguments?.getString("longtitude"),
                    backStackEntry.arguments?.getString("chosentransportationmethod")
                )
            }

            composable(
                "officechoice" +
                        "/{latitude}" +
                        "/{longtitude}" +
                        "/{chosentransportationmethod}" +
                        "/{chosenserviceid}"
            ) { backStackEntry ->
                OfficeChoiceScreen(
                    { navController.navigate("officedetails") },
                    mapView = mapView,
                    context = mapViewContext,
                    backStackEntry.arguments?.getString("latitude"),
                    backStackEntry.arguments?.getString("longtitude"),
                    backStackEntry.arguments?.getString("chosentransportationmethod"),
                    backStackEntry.arguments?.getString("chosenserviceid")
                )
            }

            composable("officedetails") { OfficeDetailsScreen() }
        }

    }
}

class MainActivity : ComponentActivity(), DrivingSession.DrivingRouteListener {

    private var mapView: MapView? = null

    private var ROUTE_START_LOCATION: Point? = null

    private var SCREEN_CENTER: Point? = null
    private var mapObjects: MapObjectCollection? = null
    private var drivingRouter: DrivingRouter? = null
    private val drivingSession: DrivingSession? = null
    var locationManager: LocationManager? = null
    var myLocationListener: LocationListener? = null
    private var myLocation: Point? = null
    private var updated = true

    private val colors = intArrayOf(-0x10000, -0xff0100, 0x00FFBBBB, -0xffff01)

    override fun onStop() {
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        locationManager!!.unsubscribe(myLocationListener!!)
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
        subscribeToLocationUpdate()
    }

    private fun subscribeToLocationUpdate() {
        if (locationManager != null && myLocationListener != null) {
            locationManager!!.subscribeForLocationUpdates(
                Companion.DESIRED_ACCURACY,
                Companion.MINIMAL_TIME, Companion.MINIMAL_DISTANCE,
                Companion.USE_IN_BACKGROUND, FilteringMode.OFF,
                myLocationListener!!
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = applicationContext
        mapView = MapView(context)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            200
        )

        MapKitFactory.initialize(context)
        DirectionsFactory.initialize(context)

        setContent {
            MoreTechVtbApp(
                mapView = mapView!!,
                context
            )
        }


        mapView!!.getMap().setRotateGesturesEnabled(false)


        val cOARSE_LOCATION =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        val fINE_LOCATION =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)

        if (cOARSE_LOCATION == PackageManager.PERMISSION_GRANTED || fINE_LOCATION == PackageManager.PERMISSION_GRANTED) {
            locationManager = MapKitFactory.getInstance().createLocationManager()


            myLocationListener = object : LocationListener {
                override fun onLocationUpdated(p0: Location) {
                    if (updated) {
                        myLocation = p0.getPosition()

                        drawMyLocationMark(myLocation!!, mapView!!)

                        ROUTE_START_LOCATION =
                            Point(myLocation!!.getLatitude(), myLocation!!.getLongitude())




                        SCREEN_CENTER = Point(
                            (ROUTE_START_LOCATION!!.getLatitude()),
                            (ROUTE_START_LOCATION!!.getLongitude())
                        )
                        mapView!!.map.move(CameraPosition(SCREEN_CENTER!!, 16f, 0f, 0f))
                    }
                }

                override fun onLocationStatusUpdated(p0: LocationStatus) {
                    if (p0 == LocationStatus.NOT_AVAILABLE) {
                        System.out.println("sdncvoadsjv")
                    }
                }

            }


        } else {
        }

    }

    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        TODO("Not yet implemented")
    }

    override fun onDrivingRoutesError(p0: Error) {
        TODO("Not yet implemented")
    }

    private fun drawMyLocationMark(point: Point, mapview: MapView) {
        val view = View(this).apply {
            background = getDrawable(R.drawable.baseline_flight_24)
        }
        mapview.map.mapObjects.addPlacemark(point, ViewProvider(view))
    }

    companion object {
        private const val REQUEST_CODE_PERMISSION = 200
        const val DESIRED_ACCURACY = 0.0
        const val MINIMAL_TIME: Long = 1000
        const val MINIMAL_DISTANCE = 1.0
        const val USE_IN_BACKGROUND = false
    }
}