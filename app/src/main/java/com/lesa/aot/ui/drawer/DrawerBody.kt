package com.lesa.aot.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lesa.aot.R
import com.lesa.aot.ui.navigation.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController
    //onItemClick: (Route) -> Unit
) {
    val routes = listOf(
        Route.Titans,
        Route.Characters,
        Route.Locations,
        Route.Organizations,
        Route.Episodes
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(routes) { route ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                        navController.navigate(route.name)
                        //onItemClick(route)
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = route.name,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}