package org.builds.nearlabs.presentation.ui.navigation

sealed class NavTarget(val route: String) {
    object Authentication : NavTarget("authentication")
}