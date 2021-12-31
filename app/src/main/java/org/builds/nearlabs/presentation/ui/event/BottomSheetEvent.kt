package org.builds.nearlabs.presentation.ui.event

import org.builds.nearlabs.presentation.ui.component.model.AuthMode

sealed class BottomSheetEvent {
    object None : BottomSheetEvent()
    class VerifyUser(val mode: AuthMode, val input: String) : BottomSheetEvent()
}