package org.builds.nearlabs.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import org.builds.nearlabs.R
import org.builds.nearlabs.presentation.ui.theme.Yellow

@Composable
fun ScreenAuthentication() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (background, logo) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .background(Yellow),
            alignment = Alignment.TopStart,
            painter = painterResource(id = R.drawable.ic_onboard),
            contentDescription = stringResource(R.string.onboard)
        )
        Image(
            modifier = Modifier
                .constrainAs(logo) {
                    bottom.linkTo(background.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(vertical = 16.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(
                id = R.string.logo
            )
        )
    }
}

@Composable
@Preview
private fun ScreenAuthenticationPreview() {
    ScreenAuthentication()
}