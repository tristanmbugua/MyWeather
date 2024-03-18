package com.example.tristan_a3.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tristan_a3.models.Weather;

class WeatherAdapter(private val data: Weather): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    public class WeatherViewHolder(view:View) :RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(com.example.tristan_a3.R.id.weather) as TextView;
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): WeatherAdapter.WeatherViewHolder{
        val view=LayoutInflater.from(parent.context).inflate(com.example.tristan_a3.R.layout.object_row,parent,false);
        return WeatherViewHolder(view);
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.textView.text = data.toString();
    }

    override fun getItemCount(): Int {
        return 1;
    }
}