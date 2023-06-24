package com.puroblast.weatherappcompose.features.forecastscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.puroblast.weatherappcompose.R
import com.puroblast.weatherappcompose.features.forecastscreen.presentation.ForecastViewModel
import com.puroblast.weatherappcompose.utils.CELSIUS
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ForecastScreen(viewModel: ForecastViewModel) {
    val state = viewModel.foreCastState.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(checkTime()),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(items = state.fiveDayForeCast) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = convertDayInString(it.dailyForeCast[0].date),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(items = it.dailyForeCast) {
                        Card(
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .padding(horizontal = 3.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(R.color.card_color))
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = convertHourInString(it.date), color = Color.White)
                                Spacer(modifier = Modifier.size(5.dp))
                                Text(
                                    text = it.temp.toInt().toString() + CELSIUS, color = Color.White
                                )
                            }
                        }

                    }
                }
            }

        }
    }

}


@Composable
private fun checkTime(): Color {
    if (Calendar.getInstance().time.hours in 8..21) {
        return colorResource(R.color.weather_day)
    }
    return colorResource(R.color.weather_night)
}

private fun convertHourInString(
    dateTime: String
): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = sdf.parse(dateTime)
    val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return simpleDateFormat.format(date)
}

private fun convertDayInString(
    dateTime: String
): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = sdf.parse(dateTime)
    val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return simpleDateFormat.format(date)
}
