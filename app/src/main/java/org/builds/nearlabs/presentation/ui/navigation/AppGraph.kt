package org.builds.nearlabs.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.builds.nearlabs.extension.handleNavEvent
import org.builds.nearlabs.presentation.ui.event.initEventHandler
import org.builds.nearlabs.presentation.ui.screen.ScreenAssets
import org.builds.nearlabs.presentation.ui.screen.ScreenAuthentication
import org.builds.nearlabs.presentation.ui.screen.ScreenHome
import org.builds.nearlabs.presentation.ui.screen.ScreenTransactions

@Composable
fun AppGraph(navController: NavHostController) {
    val eventHandler = initEventHandler()
    navController.handleNavEvent(eventHandler.navEvent)

    NavHost(navController, NavTarget.Authentication.route) {
        composable(NavTarget.Authentication.route) {
            ScreenAuthentication()
        }
        composable(NavTarget.Home.route) {
            ScreenHome()
        }
        composable(NavTarget.Assets.route) {
            ScreenAssets()
        }
        composable(NavTarget.Transaction.route) {
            ScreenTransactions()
        }
    }
}