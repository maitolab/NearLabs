package org.builds.nearlabs.presentation.ui.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import org.builds.nearlabs.R
import org.builds.nearlabs.presentation.ui.component.model.AuthMode
import org.builds.nearlabs.presentation.ui.event.BottomSheetEvent
import org.builds.nearlabs.presentation.ui.theme.Blue
import org.builds.nearlabs.presentation.ui.theme.Gray1

@Composable
fun BottomSheetVerifyUser(event: BottomSheetEvent.VerifyUser) {
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
            IconButton(modifier = Modifier.constrainAs(button) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
                onClick = { /*TODO*/ }) {
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
                append("We've sent a 6-digit verification code to\nthe email address\n")
                withStyle(style = SpanStyle(color = Blue, fontSize = 18.sp)) {
                    append(event.input)
                }
            })
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(id = R.string.enter_verification_code)
        )

        OTPTextFields(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            length = 6
        ) {

        }

        Button(modifier = Modifier.padding(top = 1.dp), onClick = { /*TODO*/ }) {
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
            onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.send_to_a_different))
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.resend_your_code))
        }
    }
}

@Composable
fun OTPTextFields(
    modifier: Modifier = Modifier,
    length: Int,
    onFilled: (code: String) -> Unit
) {
    val size = 50.dp
    var code: List<Char> by remember { mutableStateOf(listOf()) }
    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(
        modifier = modifier
            .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        (0 until length).forEach { index ->
            OutlinedTextField(
                modifier = Modifier
                    .size(size)
                    .focusOrder(focusRequester = focusRequesters[index]) {
                        focusRequesters[index + 1].requestFocus()
                    },
                textStyle = MaterialTheme.typography.body2.copy(
                    textAlign = TextAlign.Center, color = Color.Black
                ),
                singleLine = true,
                value = code.getOrNull(index = index)?.takeIf {
                    it.isDigit()
                }?.toString() ?: "",
                onValueChange = { value: String ->
                    if (focusRequesters[index].freeFocus()) {
                        val temp = code.toMutableList()
                        if (value == "") {
                            if (temp.size > index) {
                                temp.removeAt(index = index)
                                code = temp
                                focusRequesters.getOrNull(index - 1)?.requestFocus()
                            }
                        } else {
                            if (code.size > index) {
                                temp[index] = value.getOrNull(0) ?: ' '
                            } else {
                                temp.add(value.getOrNull(0) ?: ' ')
                                code = temp
                                focusRequesters.getOrNull(index + 1)?.requestFocus() ?: onFilled(
                                    code.joinToString(separator = "")
                                )
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}

@Composable
@Preview
private fun BottomSheetVerifyUserPreview() {
    BottomSheetVerifyUser(BottomSheetEvent.VerifyUser(AuthMode.Email, "test@gmail.com"))
}