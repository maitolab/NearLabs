package org.builds.nearlabs.presentation.ui.event

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.builds.nearlabs.presentation.viewmodel.NavViewModel

class EventHandler(
    private val navigationViewModel: NavViewModel
) {
    fun postNavEvent(event: NavEvent) {
        navigationViewModel.event = event
    }

    val navEvent = navigationViewModel.event
}

@Composable
fun initEventHandler() : EventHandler{
    val context = LocalContext.current as ComponentActivity
    return EventHandler(
        hiltViewModel(context)
    )
}