package com.puroblast.weatherappcompose.features.bottomnavbar


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.puroblast.weatherappcompose.utils.EMPTY_STRING


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    navBackStackEntry: NavBackStackEntry?,
    onItemClick: (BottomNavItem) -> Unit
) {

    NavigationBar(
        modifier = modifier, containerColor = Color.DarkGray
    ) {
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val selected = item.route == currentRoute
            NavigationBarItem(selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green, unselectedIconColor = Color.Gray
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = item.icon, contentDescription = EMPTY_STRING)
                        Text(text = item.name, textAlign = TextAlign.Center, fontSize = 15.sp)
                    }
                })
        }
    }
}

