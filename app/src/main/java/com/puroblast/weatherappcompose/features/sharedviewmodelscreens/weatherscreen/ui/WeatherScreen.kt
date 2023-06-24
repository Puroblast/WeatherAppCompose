package com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.WeatherSharedViewModel
import com.puroblast.weatherappcompose.utils.CELSIUS
import com.puroblast.weatherappcompose.utils.EMPTY_STRING
import com.puroblast.weatherappcompose.utils.HUMIDITY
import com.puroblast.weatherappcompose.utils.HUMIDITY_METRIC
import com.puroblast.weatherappcompose.utils.PRESSURE
import com.puroblast.weatherappcompose.utils.PRESSURE_METRIC
import com.puroblast.weatherappcompose.utils.WIND
import com.puroblast.weatherappcompose.utils.WIND_METRIC
import java.util.Locale
import kotlin.math.roundToInt


private var isDay: Boolean = true

@Composable
fun WeatherScreen(viewModel: WeatherSharedViewModel) {

    LaunchedEffect(key1 = true) {
        viewModel.setupWeatherState()
    }
    val weatherState = viewModel.weatherState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(checkDayTime(weatherState.sunsetTime, weatherState.sunriseTime))
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = checkWeatherId(weatherId = weatherState.weatherId),
                contentDescription = EMPTY_STRING
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = weatherState.description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }, fontSize = 32.sp, color = Color.White, textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = weatherState.cityName, fontSize = 32.sp, color = Color.White
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row {
                Image(
                    painter = painterResource(id = if (weatherState.temperature > 10) R.drawable.hot_temperature else R.drawable.cold_temperature),
                    contentDescription = EMPTY_STRING,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = weatherState.temperature.roundToInt().toString() + CELSIUS,
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

            CreateCard(
                cardName = PRESSURE,
                cardValue = weatherState.pressure.toString() + PRESSURE_METRIC,
                cardSize = 120.dp
            )
            CreateCard(
                cardName = HUMIDITY,
                cardValue = weatherState.humidity.toString() + HUMIDITY_METRIC,
                cardSize = 120.dp
            )
            CreateCard(
                cardName = WIND,
                cardValue = weatherState.windSpeed.toInt().toString() + WIND_METRIC,
                cardSize = 120.dp
            )

        }


    }

}

@Composable
fun checkDayTime(sunsetTime: Long, sunriseTime: Long): Color {
    if (System.currentTimeMillis() / 1000 in sunriseTime until sunsetTime) {
        return colorResource(R.color.weather_day)
    }
    isDay = false
    return colorResource(id = R.color.weather_night)
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
fun CreateCard(cardName: String, cardValue: String, cardSize: Dp) {
    Card(
        modifier = Modifier
            .size(cardSize)
            .alpha(0.8F)
            .padding(bottom = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(R.color.card_color),
            contentColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = cardName, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(3.dp))
            Text(text = cardValue, textAlign = TextAlign.Center)
        }
    }
}

