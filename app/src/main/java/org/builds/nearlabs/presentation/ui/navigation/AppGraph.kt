package org.builds.nearlabs.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.builds.nearlabs.presentation.ui.screen.ScreenAuthentication

@Composable
fun AppGraph() {
    val navController = rememberNavController()

    NavHost(navController, NavTarget.Authentication.route) {
        composable(NavTarget.Authentication.route) {
            ScreenAuthentication()
        }
    }
}