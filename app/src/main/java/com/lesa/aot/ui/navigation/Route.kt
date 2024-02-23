package com.lesa.aot.ui.navigation

sealed class Route (
    val name: String
) {
    data object Titans: Route(name = "Titans")
    data object Titan: Route(name = "Titan")
    data object Characters: Route("Characters")
    data object Locations: Route("Locations")
    data object Organizations: Route("Organizations")
    data object Episodes: Route("Episodes")
}


