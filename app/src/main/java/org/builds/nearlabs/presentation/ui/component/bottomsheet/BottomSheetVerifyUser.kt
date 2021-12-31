package org.builds.nearlabs.presentation.ui.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import org.builds.nearlabs.R
import org.builds.nearlabs.presentation.ui.component.OTPTextFields
import org.builds.nearlabs.presentation.ui.component.model.AuthMode
import org.builds.nearlabs.presentation.ui.event.BottomSheetEvent
import org.builds.nearlabs.presentation.ui.event.SnackBarEvent
import org.builds.nearlabs.presentation.ui.event.initEventHandler
import org.builds.nearlabs.presentation.ui.theme.Blue
import org.builds.nearlabs.presentation.ui.theme.Gray1

@Composable
fun BottomSheetVerifyUser(event: BottomSheetEvent.VerifyUser) {
    val eventHandler = initEventHandler()
    val resendMessage = stringResource(id = R.string.resend_your_code)
    BottomSheetVerifyUser(event,
        onContinue = {
            eventHandler.postBottomSheetEvent(BottomSheetEvent.CreateNearAccount)
        },
        onSendDifferentUser = {
            eventHandler.postBottomSheetEvent(BottomSheetEvent.None)
        }, onResend = {
            eventHandler.postSnackBarEvent(SnackBarEvent.Info(resendMessage))
        })
}

@Composable
private fun BottomSheetVerifyUser(
    event: BottomSheetEvent.VerifyUser,
    onContinue: () -> Unit,
    onSendDifferentUser: () -> Unit,
    onResend: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (text, button) = createRefs()
            Text(
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(),
                text = stringResource(R.string.verification),
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
            IconButton(
                modifier = Modifier.constrainAs(button) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                onClick = onContinue
            ) {
                Icon(Icons.Default.Close, contentDescription = null, tint = Gray1)
            }
        }

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = 0.33f,
            backgroundColor = Color.White
        )

        Text(
            modifier = Modifier.padding(top = 32.dp),
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                append("We've sent a 6-digit verification code to\n")
                append(if (event.mode.isEmail()) "the email address\n" else "your phone\n")
                withStyle(style = SpanStyle(color = Blue, fontSize = 18.sp)) {
                    append(event.input)
                }
            })
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.enter_verification_code)
        )

        var otpIsFilled by remember { mutableStateOf(false) }

        val otpLength = 6
        OTPTextFields(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            length = otpLength
        ) {
            otpIsFilled = it.length == otpLength
        }

        Button(
            modifier = Modifier.padding(top = 1.dp),
            onClick = onContinue,
            enabled = otpIsFilled
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string._continue))
                Icon(Icons.Default.KeyboardArrowRight, "")
            }
        }
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = stringResource(R.string.did_receive_your_code)
        )
        TextButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onSendDifferentUser
        ) {
            Text(
                text = stringResource(
                    R.string.send_to_a_different,
                    if (event.mode.isEmail()) "email address" else "phone number"
                )
            )
        }
        TextButton(onClick = onResend) {
            Text(text = stringResource(R.string.resend_your_code))
        }
    }
}


@Composable
@Preview
private fun BottomSheetVerifyUserPreview() {
    BottomSheetVerifyUser(BottomSheetEvent.VerifyUser(AuthMode.Email, "test@gmail.com"), {}, {}, {})
}