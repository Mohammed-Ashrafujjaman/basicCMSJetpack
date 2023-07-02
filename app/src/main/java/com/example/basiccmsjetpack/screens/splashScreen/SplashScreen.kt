package com.example.basiccmsjetpack.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.basiccmsjetpack.R
import com.example.basiccmsjetpack.Screen
import com.example.basiccmsjetpack.ui.theme.BasicCMSJetpackTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(route = Screen.UserListScreen.route)
    }
    SplashScreenSkeleton()
}

@Composable
fun SplashScreenSkeleton() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cms),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BasicCMSJetpackTheme {
        SplashScreenSkeleton()
    }
}
