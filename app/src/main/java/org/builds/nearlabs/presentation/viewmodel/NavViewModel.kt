package org.builds.nearlabs.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.builds.nearlabs.presentation.ui.event.NavEvent

class NavViewModel {
    var event by mutableStateOf<NavEvent>(NavEvent.None)
}