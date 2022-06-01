package com.mindgarden.mindgarden.presentation.diary.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.ItemWeatherBinding

class WeatherAdapter(private val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weathers = Weather.values()

    private var selectedPos = -1
    private var lastItemSelectedPos = -1

    inner class WeatherViewHolder(val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.weatherContainer.setOnClickListener {
                onClick(adapterPosition)
                // set selected
                selectedPos = adapterPosition
                lastItemSelectedPos = if (lastItemSelectedPos == -1)
                    selectedPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedPos
                }
                notifyItemChanged(selectedPos)
            }
        }

        fun bind(item: Weather) {
            binding.tvWeather.text = item.defaultText
            Glide.with(binding.ivWeather.context)
                .load(item.img)
                .into(binding.ivWeather)

            if (adapterPosition == selectedPos) setSelected()
            else setDefault()
        }

        private fun setDefault() {
            binding.weatherContainer.background = null
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun setSelected() {
            binding.weatherContainer.background =
                binding.weatherContainer.context.getDrawable(R.drawable.border_garden)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.bind(weathers[position])

    override fun getItemCount(): Int = weathers.size
}
