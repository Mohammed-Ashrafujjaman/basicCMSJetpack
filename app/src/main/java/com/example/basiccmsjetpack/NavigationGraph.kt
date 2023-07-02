package com.example.basiccmsjetpack

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.basiccmsjetpack.screens.home.users.UserList
import com.example.basiccmsjetpack.screens.home.users.UserListViewModel
import com.example.basiccmsjetpack.screens.splashScreen.SplashScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.UserListScreen.route) {
            val viewModel: UserListViewModel = hiltViewModel()
            UserList(navController = navController, viewModel = viewModel)
        }
    }
}
