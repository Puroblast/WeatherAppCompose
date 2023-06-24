package com.puroblast.weatherappcompose.features.sharedviewmodelscreens.detailsscreen.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.WeatherSharedViewModel
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.CreateCard
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.checkDayTime
import com.puroblast.weatherappcompose.utils.CELSIUS
import com.puroblast.weatherappcompose.utils.CLOUDINESS
import com.puroblast.weatherappcompose.utils.DEGREE
import com.puroblast.weatherappcompose.utils.EMPTY_STRING
import com.puroblast.weatherappcompose.utils.FEELS_LIKE
import com.puroblast.weatherappcompose.utils.HUMIDITY
import com.puroblast.weatherappcompose.utils.HUMIDITY_METRIC
import com.puroblast.weatherappcompose.utils.MAX_TEMP
import com.puroblast.weatherappcompose.utils.MIN_TEMP
import com.puroblast.weatherappcompose.utils.NEW_LINE
import com.puroblast.weatherappcompose.utils.PRESSURE
import com.puroblast.weatherappcompose.utils.PRESSURE_METRIC
import com.puroblast.weatherappcompose.utils.SUNRISE
import com.puroblast.weatherappcompose.utils.SUNSET
import com.puroblast.weatherappcompose.utils.VISIBILITY
import com.puroblast.weatherappcompose.utils.WIND
import com.puroblast.weatherappcompose.utils.WIND_METRIC
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.roundToInt

@Composable
fun DetailsScreen(viewModel: WeatherSharedViewModel) {

    LaunchedEffect(key1 = true) {
        viewModel.setupDetailsState()
    }
    val detailsState = viewModel.detailsState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(checkDayTime(detailsState.sunsetTime, detailsState.sunriseTime))
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = detailsState.cityName, fontSize = 32.sp, color = Color.White
                )
                Spacer(modifier = Modifier.size(10.dp))
                Row {
                    Image(
                        painter = painterResource(id = if (detailsState.temperature > 10) R.drawable.hot_temperature else R.drawable.cold_temperature),
                        contentDescription = EMPTY_STRING,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = detailsState.temperature.roundToInt().toString() + CELSIUS,
                        fontSize = 32.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )
                }

            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 5.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom,

                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CreateCard(
                        cardName = MAX_TEMP,
                        cardValue = detailsState.maximalTemperature.toInt().toString() + CELSIUS,
                        cardSize = 160.dp
                    )
                    CreateCard(
                        cardName = MIN_TEMP,
                        cardValue = detailsState.minimalTemperature.toInt().toString() + CELSIUS,
                        cardSize = 160.dp
                    )

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CreateCard(
                        cardName = PRESSURE,
                        cardValue = detailsState.pressure.toString() + PRESSURE_METRIC,
                        cardSize = 160.dp
                    )
                    CreateCard(
                        cardName = HUMIDITY,
                        cardValue = detailsState.humidity.toString() + HUMIDITY_METRIC,
                        cardSize = 160.dp
                    )


                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CreateCard(
                        cardName = WIND,
                        cardValue = detailsState.windSpeed.toInt()
                            .toString() + WIND_METRIC + NEW_LINE + detailsState.windDegree.toString() + DEGREE,
                        cardSize = 160.dp
                    )
                    CreateCard(
                        cardName = FEELS_LIKE,
                        cardValue = detailsState.feelsLike.toInt().toString() + CELSIUS,
                        cardSize = 160.dp
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CreateCard(
                        cardName = VISIBILITY,
                        cardValue = detailsState.visibility.toString(),
                        cardSize = 160.dp
                    )
                    CreateCard(
                        cardName = CLOUDINESS,
                        cardValue = detailsState.cloudiness.toString(),
                        cardSize = 160.dp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CreateCard(
                        cardName = SUNRISE,
                        cardValue = getTime(detailsState.sunriseTime),
                        cardSize = 160.dp
                    )
                    CreateCard(
                        cardName = SUNSET,
                        cardValue = getTime(detailsState.sunsetTime),
                        cardSize = 160.dp
                    )


                }

            }

        }
    }
}

private fun getTime(time: Long): String {
    val format = "HH:mm"
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(Date(time * 1000))
}

