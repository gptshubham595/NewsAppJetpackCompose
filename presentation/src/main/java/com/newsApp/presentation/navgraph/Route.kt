package com.newsApp.presentation.navgraph

sealed class Route (val route: String){
    object OnBoardingScreen : Route("onboarding")
    object HomeScreen : Route("home")
    object BookMarkScreen : Route("bookmark")
    object DetailsScreen : Route("details")
    object SearchScreen : Route("search")

    //subgraph
    object AppStartNavigation : Route("appStartNavigation")

    //subgraph
    object NewsNavigation : Route("newsNavigation")
    object NewsNavigatorScreen : Route("newsNavigator")

}
