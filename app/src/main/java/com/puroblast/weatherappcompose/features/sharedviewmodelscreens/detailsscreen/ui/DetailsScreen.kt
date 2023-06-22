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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.detailsscreen.presentation.DetailsState
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.CreateCard
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.checkDayTime
import com.puroblast.weatherappcompose.features.sharedviewmodelscreens.weatherscreen.ui.checkWeatherId
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

@Composable
fun DetailsScreen(detailsState: DetailsState) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(checkDayTime(detailsState.sunsetTime, detailsState.sunriseTime))
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = checkWeatherId(weatherId = detailsState.weatherId),
                contentDescription = EMPTY_STRING
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = detailsState.description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                },
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = detailsState.cityName,
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(25.dp))
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

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            CreateCard(
                cardName = PRESSURE,
                cardValue = detailsState.pressure.toString() + PRESSURE_METRIC
            )
            CreateCard(
                cardName = HUMIDITY,
                cardValue = detailsState.humidity.toString() + HUMIDITY_METRIC
            )
            CreateCard(
                cardName = WIND,
                cardValue = detailsState.windSpeed.toInt().toString() + WIND_METRIC
            )

        }


    }
}