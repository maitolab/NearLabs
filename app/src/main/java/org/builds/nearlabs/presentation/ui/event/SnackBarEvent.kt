package org.builds.nearlabs.presentation.ui.event

sealed class SnackBarEvent {
    object None : SnackBarEvent()
    class Error(val error: String) : SnackBarEvent()
    class Info(val message: String) : SnackBarEvent()
}