package com.lesa.aot.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lesa.aot.ui.screens.characterlist.CharacterListScreen
import com.lesa.aot.ui.screens.characterlist.CharacterListViewModel
import com.lesa.aot.ui.screens.titan.TitanScreen
import com.lesa.aot.ui.screens.titan.TitanViewModel
import com.lesa.aot.ui.screens.titans.TitansScreen
import com.lesa.aot.ui.screens.titans.TitansViewModel

@Composable
fun Navigation(
    drawerState: DrawerState,
    navController: NavHostController,
    screen: MutableState<String>
) {
    NavHost(
        navController = navController, startDestination = Route.Titans.name
    ) {
        composable(
            route = Route.Titans.name
        ) {
            screen.value = Route.Titans.name
            val titansViewModel = hiltViewModel<TitansViewModel>()
            TitansScreen(
                viewModel = titansViewModel,
                onItemClicked = { navController.navigate(Route.Titan.name + "/$it") }
            )
            drawerState.isClosed
        }
        composable(
            route = Route.Titan.name + "/{titan_name}",
            arguments = listOf(
                navArgument("titan_name") {
                    type = NavType.StringType
                }
            )
        ) {
            val titanName = it.arguments?.getString("titan_name")
            val titanViewModel = TitanViewModel(titanName = titanName!!) //TODO убрать воскликалки
            screen.value = titanName
            TitanScreen(
                viewModel = titanViewModel
            )
            drawerState.isClosed
        }
        composable(
            route = Route.Characters.name
        ) {
            val characterListViewModel = hiltViewModel<CharacterListViewModel>()
            screen.value = Route.Characters.name
            CharacterListScreen(
                viewModel = characterListViewModel
            )
        }
        composable(
            route = Route.Locations.name
        ) {
            screen.value = Route.Locations.name
        }
        composable(
            route = Route.Organizations.name
        ) {
            screen.value = Route.Organizations.name
        }
        composable(
            route = Route.Episodes.name
        ) {
            screen.value = Route.Episodes.name
        }
    }
}