package com.example.jetweatherforecast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.example.jetweatherforecast.data.DataOrException
import com.example.jetweatherforecast.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    ShowData(mainViewModel)
}

@Composable

fun ShowData(mainViewModel: MainViewModel) {
    val weatherData =
        produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(
                loading = true
            )
        ) {
            value = mainViewModel.getWeatherData("seattle","imperial")
        }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        Text(text = "Main Screen  ${weatherData.data!!.city.name}")
    }
}
