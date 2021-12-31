package org.builds.nearlabs.extension

import android.util.Log
import androidx.navigation.NavHostController
import org.builds.nearlabs.presentation.ui.event.NavEvent

fun NavHostController.handleNavEvent(navEvent: NavEvent) {
    when (navEvent) {
        is NavEvent.Action -> {
            navigate(navEvent.target.route)
        }
        is NavEvent.ActionInclusive -> {
            navigate(navEvent.target.route) {
                popUpTo(navEvent.inclusiveTarget.route) {
                    inclusive = true
                }
            }
        }
        is NavEvent.PopBackStack -> {
            popBackStack(navEvent.target.route, inclusive = navEvent.inclusive)
        }
        is NavEvent.NavigateUp -> {
            navigateUp()
        }
        else -> Log.i("NearLabs", "No need to handle $navEvent")
    }
}