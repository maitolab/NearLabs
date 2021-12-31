package org.builds.nearlabs.presentation.ui.event

import org.builds.nearlabs.presentation.viewmodel.NavViewModel

class EventHandler(
    private val navigationViewModel: NavViewModel
) {
    fun postNavEvent(event: NavEvent) {
        navigationViewModel.event = event
    }

    val navEvent = navigationViewModel.event
}