package com.lesa.aot.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lesa.aot.R
import com.lesa.aot.ui.navigation.Navigation
import com.lesa.aot.ui.navigation.Route
import com.lesa.aot.ui.theme.AotAccentYellow
import com.lesa.aot.ui.theme.AotMutedRedGhost
import com.lesa.aot.ui.theme.aotFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    coroutineScope: CoroutineScope = rememberCoroutineScope()
    ) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController: NavHostController = rememberNavController()
    val screen = remember {
        mutableStateOf(Route.Titans.name)
    }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    //.fillMaxSize()
                ,
                drawerShape = RectangleShape,
                windowInsets = WindowInsets(0.dp),
                drawerContainerColor = Transparent
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.bg_4),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        //colorFilter = ColorFilter.tint(AotMutedRedGhost)
                    )
                    Column(
                        Modifier.background(AotMutedRedGhost)
                    ) {
                        DrawerHeader()
                        DrawerBody(
                            scope = coroutineScope,
                            drawerState = drawerState,
                            navController = navController

                        )
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                //.border(1.dp, AotAccentYellow)

        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_4),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                //colorFilter = ColorFilter.tint(AotMutedRedGhost)
            )
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = screen.value,
                                fontFamily = aotFontFamily,
                                fontSize = 30.sp
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(imageVector = Icons.Sharp.Menu, contentDescription = "menu")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Transparent
                        ),
                        modifier = Modifier
                            //.border(2.dp, AotAccentYellow)
                    )
                },
                containerColor = AotMutedRedGhost
            ) {
                Column(
                    Modifier.padding(paddingValues = it)
                ) {
                    Divider(
                        thickness = 2.dp,
                        color = AotAccentYellow
                    )
                    Navigation(
                        drawerState = drawerState,
                        navController = navController,
                        screen = screen
                    )
                }
            }
        }
    }
}

