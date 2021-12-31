package org.builds.nearlabs.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.builds.nearlabs.presentation.ui.event.SnackBarEvent
import javax.inject.Inject

@HiltViewModel
class SnackBarViewModel @Inject constructor() : ViewModel() {
    var event by mutableStateOf<SnackBarEvent>(SnackBarEvent.None)
}