package com.example.basiccmsjetpack

sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = "Splash_Screen")
    object UserListScreen : Screen(route = "User_List_Screen")
}
