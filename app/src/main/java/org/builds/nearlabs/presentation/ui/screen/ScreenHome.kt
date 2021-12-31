package org.builds.nearlabs.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import org.builds.nearlabs.R
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.domain.model.transaction.Transaction
import org.builds.nearlabs.presentation.ui.component.Header
import org.builds.nearlabs.presentation.ui.screen.components.AssetItem
import org.builds.nearlabs.presentation.ui.screen.components.TransactionItem
import org.builds.nearlabs.presentation.ui.screen.components.UserInfo
import org.builds.nearlabs.presentation.ui.theme.Blue
import org.builds.nearlabs.presentation.ui.theme.Gray2
import org.builds.nearlabs.presentation.viewmodel.AssetViewModel
import org.builds.nearlabs.presentation.viewmodel.TransactionViewModel

@Composable
@Preview
fun ScreenHome() {
    val assetViewModel = hiltViewModel<AssetViewModel>()
    val transactionViewModel = hiltViewModel<TransactionViewModel>()

    val popularAssets: MutableState<ResultWrapper<List<Asset>>> = remember {
        mutableStateOf(ResultWrapper.None)
    }

    val recentTransactions: MutableState<ResultWrapper<List<Transaction>>> = remember {
        mutableStateOf(ResultWrapper.None)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            UserInfo()
        }

        item {
            Banner()
        }

        if (popularAssets.value is ResultWrapper.Success) {
            item {
                Header(
                    header = stringResource(id = R.string.my_nft),
                    action = stringResource(id = R.string.see_all)
                )
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(popularAssets.value.takeValueOrThrow()) {
                        Box(
                            modifier = Modifier
                                .width(230.dp)
                                .wrapContentHeight()
                        ) {
                            AssetItem(it)
                        }
                    }
                }
            }
        }

        if (recentTransactions.value is ResultWrapper.Success) {
            item {
                Header(
                    header = stringResource(id = R.string.recent_transaction),
                    action = stringResource(id = R.string.see_all)
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    recentTransactions.value.takeValueOrThrow().forEach {
                        TransactionItem(it)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = "fetchData") {
        assetViewModel.getPopularAssets(popularAssets)
        transactionViewModel.getRecentTransactions(recentTransactions)
    }
}


@Composable
private fun Banner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, Gray2),
    ) {
        ConstraintLayout {
            val (image, title, button) = createRefs()
            Image(
                modifier = Modifier.constrainAs(image) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                },
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(button.top)
                    width = Dimension.percent(0.5f)
                },
                text = stringResource(id = R.string.banner_slogan),
                fontSize = 26.sp
            )

            Button(
                modifier = Modifier
                    .constrainAs(button) {
                        start.linkTo(title.start)
                        top.linkTo(title.bottom, margin = 12.dp)
                        bottom.linkTo(parent.bottom, margin = 12.dp)
                    }
                    .background(Blue, RoundedCornerShape(10.dp)),
                onClick = { }) {
                Text(
                    text = stringResource(id = R.string.create_nft),
                    color = Color.White
                )
            }
        }
    }
}
