package com.android.pos.ui.navigation

enum class RouteGroups {
    HOME,
    SHOP,
    PRODUCTS,
    YOU
}

/**
 * Interface to describe the navigation destinations for the app
 */
interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    val titleRes: Int

    /**
     * name of
     */
    val routeGroup : RouteGroups?
}
