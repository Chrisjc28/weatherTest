package com.example.weathertestapp.favouritefeature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.ActivityFavouriteDetailBinding
import com.example.weathertestapp.models.db.CityWeather
import com.example.weathertestapp.viewmodels.FavouriteCityWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

private val LOG_TAG = FavouriteDetailActivity::class.java.simpleName
const val CITY_ID = "city_id"

class FavouriteDetailActivity : AppCompatActivity() {

    private val favouriteCityWeatherViewModel: FavouriteCityWeatherViewModel by viewModel()

    private lateinit var binding: ActivityFavouriteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = "Detailed city weather"

        val cityId = intent.getIntExtra(CITY_ID, 0)

        favouriteCityWeatherViewModel.getCityById(cityId).observe(this, Observer {
            setUpCityName(it)
            setUpCountryName(it)
            setUpLatitude(it)
            setUpLongitude(it)
            setUpSunRise(it)
            setUpSunSet(it)
            setUpWindInDegrees(it)
            setUpWindSpeed(it)
            setUpTemp(it)
            setUpFeelLikeTemp(it)
            setUpMaxTemp(it)
            setUpMinTemp(it)
            setUpHumidity(it)
            setUpWeatherDescription(it)
        })
    }

    private fun setUpWeatherDescription(it: CityWeather) {
        binding.moreDetailWeatherDescriptionTextView.text =
            resources.getString(
                R.string.weather_description,
                it.weatherDescription
            )
    }

    private fun setUpHumidity(it: CityWeather) {
        binding.moreDetailHumidityTextView.text =
            resources.getString(
                R.string.humidity_text,
                it.humidity
            )
    }

    private fun setUpMinTemp(it: CityWeather) {
        binding.moreDetailMinTempTextView.text =
            resources.getString(
                R.string.min_temp_text,
                (it.minTemp!! - 273.15)
            )
    }

    private fun setUpMaxTemp(it: CityWeather) {
        binding.moreDetailMaxTempTextView.text =
            resources.getString(
                R.string.max_temp_text,
                (it.maxTemp!! - 273.15)
            )
    }

    private fun setUpFeelLikeTemp(it: CityWeather) {
        binding.moreDetailFeelsLikeTempTextView.text =
            resources.getString(
                R.string.feels_like_temp_text,
                (it.feelsLikeTemp!! - 273.15)
            )
    }

    private fun setUpTemp(it: CityWeather) {
        binding.moreDetailTempTextView.text =
            resources.getString(
                R.string.temp_text,
                (it.temp!! - 273.15)
            )
    }

    private fun setUpWindSpeed(it: CityWeather) {
        binding.moreDetailWindSpeedTextView.text =
            resources.getString(
                R.string.wind_speed_text,
                it.windSpeed.toString()
            )
    }

    private fun setUpWindInDegrees(it: CityWeather) {
        binding.moreDetailWindInDegreesTextView.text =
            resources.getString(
                R.string.wind_in_degrees_text,
                it.windInDegrees.toString()
            )
    }

    private fun setUpSunSet(it: CityWeather) {
        val sunSetString = convertLongToTime(it.sunset!!)

        binding.moreDetailSunsetTextView.text =
            resources.getString(
                R.string.sunset_text,
                sunSetString
            )
    }

    private fun setUpSunRise(it: CityWeather) {
        val sunRiseString = convertLongToTime(it.sunrise!!)

        binding.moreDetailSunriseTextView.text =
            resources.getString(
                R.string.sunrise_text,
                sunRiseString
            )
    }

    private fun setUpLongitude(it: CityWeather) {
        binding.moreDetailLongitudeTextView.text = resources.getString(
            R.string.longitude_text,
            it.longitude
        )
    }

    private fun setUpLatitude(it: CityWeather) {
        binding.moreDetailLatitudeTextView.text = resources.getString(
            R.string.latitude_text,
            it.latitude
        )
    }

    private fun setUpCountryName(it: CityWeather) {
        binding.moreDetailCountryTextView.text =
            resources.getString(R.string.country_name_text, it.country)
    }

    private fun setUpCityName(it: CityWeather) {
        binding.moreDetailCityNameTextView.text =
            resources.getString(R.string.city_name_text, it.name)
    }

   private fun convertLongToTime(time: Long): String {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.UK)
        return format.format(date)
    }
}