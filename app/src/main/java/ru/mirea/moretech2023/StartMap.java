package ru.mirea.moretech2023;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

import ru.mirea.moretech2023.network.ApiKeys;

public class StartMap extends Application {
    private final String MAPKIT_API_KEY = ApiKeys.YANDEX_MAPS_FOR_ORGS_KEY;
    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
