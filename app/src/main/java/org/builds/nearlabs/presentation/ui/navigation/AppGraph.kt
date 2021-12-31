package org.builds.nearlabs.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.builds.nearlabs.presentation.ui.screen.ScreenAssets
import org.builds.nearlabs.extension.handleNavEvent
import org.builds.nearlabs.presentation.ui.event.EventHandler
import org.builds.nearlabs.presentation.ui.event.initEventHandler
import org.builds.nearlabs.presentation.ui.screen.ScreenHome
import org.builds.nearlabs.presentation.ui.screen.ScreenAuthentication
import org.builds.nearlabs.presentation.ui.screen.ScreenTransactions

@Composable
fun AppGraph() {
    val navController = rememberNavController()
    val eventHandler = initEventHandler()
    navController.handleNavEvent(eventHandler.navEvent)

    NavHost(navController, NavTarget.Transaction.route) {
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