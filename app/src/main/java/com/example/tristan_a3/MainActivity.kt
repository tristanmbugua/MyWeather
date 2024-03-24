package com.example.tristan_a3

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tristan_a3.adapters.WeatherAdapter
import com.example.tristan_a3.databinding.MainActivityBinding
import com.example.tristan_a3.helpers.LocationHelper
import com.example.tristan_a3.models.Response
import com.example.tristan_a3.models.Weather
import com.example.tristan_a3.network.API_Response
import com.example.tristan_a3.network.RetrofitInstance
import com.example.tristan_a3.ui.theme.Tristan_A3Theme
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LastLocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.Tasks.await
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.util.Locale
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    lateinit var locationService: FusedLocationProviderClient;
    lateinit var weather: Weather;
    lateinit var response: Response;
    lateinit var binding: MainActivityBinding;
    var longitude: Double = -1.0;
    var latitude: Double = -1.0;
    lateinit var locationRequest: LocationRequest;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = MainActivityBinding.inflate(layoutInflater);
        setContentView(this.binding.root);
        locationService = LocationHelper.instance.getFusedLocationProviderClient(this);

        locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 100)
                .setWaitForAccurateLocation(true)
                .setGranularity(Granularity.GRANULARITY_FINE).build();


        var locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                latitude = locationResult.lastLocation!!.latitude;
                longitude = locationResult.lastLocation!!.longitude;
                getWeather();
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == 0
            &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == 0
        ) { } else { this.checkPermissions(this);  }

        this.checkPermissions(this);

        locationService.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

    }

    fun getWeather() {
        var api: API_Response = RetrofitInstance.retrofitService;

        lifecycleScope.launch {
            response = api.getWeather(
                "0ad1145b571944e0bda143730241803",
                "${latitude},${longitude}"
            );

            createUI(response.weather, response.location);
        }
    }

    fun createUI(weather: Weather, location: com.example.tristan_a3.models.Location) {
        binding.name.text =  "City: " + location.name;
        binding.region.text = "Region: " + location.region;
        binding.country.text = "Country: " + location.country;

        binding.lastUpdate.text = "This information is accurate as of: " + weather.last_updated;
        binding.tempC.text = "Temperature in celcius: " + weather.temp_c.toString();
        binding.feelsLikeC.text = "Feels like: " + weather.feelslike_c.toString() + " celcius.";
        binding.windKPH.text = "Wind in KPH: " + weather.wind_kph.toString();
        binding.windDir.text = "Wind direction: " + weather.wind_dir;
        if (weather.is_day == 1.0) {
            binding.isDay.text = "It is daytime.";
        } else {
            binding.isDay.text = "It is nighttime.";
        }
        binding.precip.text = "Precipitation: " + weather.precip_mm.toString() + "%";
        binding.humidity.text = "Humidity: " + weather.humidity.toString() + "%";
        binding.cloud.text = "Cloudiness: " + weather.cloud.toString() + "%";
        binding.uv.text = "UV points: " + weather.uv.toString();
    }

    fun checkPermissions(context: Context){

        if ((
                    ContextCompat.checkSelfPermission(
                        context.applicationContext,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
            ContextCompat.checkSelfPermission(
                context.applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            Log.d(TAG, "checkPermission: Permission Granted");
        }else{
            Log.d(TAG, "checkPermission: Permission Denied");
            this.requestLocationPermissions(context)
        }
    }

    private fun requestLocationPermissions(context: Context) {
        val listOfPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions( (context) as Activity, listOfPermissions, 101)
    }
}