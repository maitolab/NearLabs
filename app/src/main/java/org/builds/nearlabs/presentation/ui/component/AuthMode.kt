package org.builds.nearlabs.presentation.ui.component

import androidx.compose.ui.text.input.KeyboardType
import org.builds.nearlabs.R
import org.builds.nearlabs.presentation.ui.theme.Gray2
import org.builds.nearlabs.presentation.ui.theme.Gray3

enum class AuthMode {
    Email,
    Phone;

    fun getSelectionColor(currentMode: AuthMode) = if (this == currentMode) Gray2 else Gray3
    fun getLabel() = when (this) {
        Phone -> R.string.phone_label
        Email -> R.string.email_label
    }

    fun getKeyboardType() = when (this) {
        Phone -> KeyboardType.Phone
        Email -> KeyboardType.Email
    }

    fun isEmail() = this == Email
}