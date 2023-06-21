package com.puroblast.weatherappcompose.features.weatherscreen.ui


import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.weatherscreen.presentation.WeatherViewModel
import kotlin.math.roundToInt


private var isDay: Boolean = true

@Composable
fun WeatherScreen(navHostController: NavHostController, viewModel: WeatherViewModel) {

    val weatherState by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(checkDayTime(weatherState.sunsetTime))
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = checkWeatherId(weatherId = weatherState.weatherId),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = weatherState.description,
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = weatherState.cityName,
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(25.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.hot_temperature),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = weatherState.temperature.roundToInt().toString() + " \u2103",
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
            }

        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            CreateCard(cardName = "Pressure", cardValue = weatherState.pressure.toString() + " Pa")
            CreateCard(cardName = "Humidity", cardValue = weatherState.humidity.toString() + " %")
            CreateCard(cardName = "Wind", cardValue = weatherState.windSpeed.toString() + " m/s")

        }


    }

}

@Composable
fun checkDayTime(sunsetTime: Int): Color {
    if (System.currentTimeMillis() / 1000 - sunsetTime >= 0) {
        isDay = false
        return colorResource(id = R.color.weather_night)
    }
    return colorResource(R.color.weather_day)
}

@Composable
fun checkWeatherId(weatherId: Int): Painter {
    return when (weatherId) {
        in 200..232 -> painterResource(id = R.drawable.thunderstorm)
        in 300..321, in 520..531 -> painterResource(id = R.drawable.shower_rain)
        in 500..504 -> if (isDay) painterResource(id = R.drawable.rain_day) else painterResource(id = R.drawable.rain_night)
        511, in 600..622 -> painterResource(id = R.drawable.snow)
        in 701..781 -> painterResource(id = R.drawable.mist)
        800 -> if (isDay) painterResource(id = R.drawable.clear_sky_day) else painterResource(id = R.drawable.clear_sky_night)
        801 -> if (isDay) painterResource(id = R.drawable.few_clouds_day) else painterResource(id = R.drawable.few_clouds_night)
        802 -> painterResource(id = R.drawable.scattered_clouds)
        803, 804 -> painterResource(id = R.drawable.broken_clouds)
        else -> if (isDay) painterResource(id = R.drawable.clear_sky_day) else painterResource(id = R.drawable.clear_sky_night)

    }
}

@Composable
fun CreateCard(cardName : String , cardValue : String) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .alpha(0.8F),
        colors = CardDefaults.cardColors(
            containerColor = Color(R.color.card_color),
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = cardName)
            Text(text = cardValue)
        }
    }
}
