package com.example.tristan_a3.helpers

import android.content.Context
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationHelper private constructor() {

    //to access device location
    private var fusedLocationProviderClient : FusedLocationProviderClient? = null
    //location request configuration
    private var locationRequest : LocationRequest

    //singleton design pattern
    companion object{
        val instance = LocationHelper()
    }

    init {
        this.locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build()
    }

    fun getFusedLocationProviderClient(context: Context) : FusedLocationProviderClient{

        if (fusedLocationProviderClient == null){
            this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        }

        return this.fusedLocationProviderClient!!
    }
}










