package com.example.tristan_a3

import android.os.Bundle
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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tristan_a3.adapters.WeatherAdapter
import com.example.tristan_a3.databinding.MainActivityBinding
import com.example.tristan_a3.models.Weather
import com.example.tristan_a3.network.API_Response
import com.example.tristan_a3.network.RetrofitInstance
import com.example.tristan_a3.ui.theme.Tristan_A3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    lateinit var response: Weather;
    lateinit var binding: MainActivityBinding;
    lateinit var longitude: Number;
    lateinit var latitude: Number;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = MainActivityBinding.inflate(layoutInflater);
        setContentView(this.binding.root);
        longitude = 43.6600832;
        latitude = -79.5017216;
        getWeather();
    }

    fun getWeather() {
        binding.rvWeather.layoutManager= LinearLayoutManager(this@MainActivity);
        var api: API_Response = RetrofitInstance.retrofitService;

        lifecycleScope.launch {
            response = api.getWeather(
                "0ad1145b571944e0bda143730241803",
                latitude.toString()+","+longitude.toString()
            );
            createRecyclerView();
        }
    }

    fun createRecyclerView() {
        var weatherAdapter = WeatherAdapter(response);
        binding.rvWeather.adapter = weatherAdapter;
    }
}