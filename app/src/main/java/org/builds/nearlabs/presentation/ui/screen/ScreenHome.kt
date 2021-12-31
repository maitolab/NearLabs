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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import org.builds.nearlabs.R
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.domain.model.transaction.Transaction
import org.builds.nearlabs.presentation.ui.component.Header
import org.builds.nearlabs.presentation.ui.component.AssetItem
import org.builds.nearlabs.presentation.ui.component.TransactionItem
import org.builds.nearlabs.presentation.ui.event.NavEvent
import org.builds.nearlabs.presentation.ui.event.initEventHandler
import org.builds.nearlabs.presentation.ui.navigation.NavTarget
import org.builds.nearlabs.presentation.ui.screen.components.UserInfo
import org.builds.nearlabs.presentation.ui.theme.Blue
import org.builds.nearlabs.presentation.ui.theme.Gray4
import org.builds.nearlabs.presentation.viewmodel.initAssetViewModel
import org.builds.nearlabs.presentation.viewmodel.initTransactionViewModel

@Composable
@Preview
fun ScreenHome() {
    val eventHandler = initEventHandler()
    val assetViewModel = initAssetViewModel()
    val transactionViewModel = initTransactionViewModel()

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
            Banner {
                eventHandler.postNavEvent(NavEvent.Action(NavTarget.CreateNFT))
            }
        }

        if (popularAssets.value is ResultWrapper.Success) {
            item {
                Header(
                    header = stringResource(id = R.string.my_nft),
                    action = stringResource(id = R.string.see_all)
                ) {
                    eventHandler.postNavEvent(NavEvent.Action(NavTarget.Assets))
                }
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(popularAssets.value.takeValueOrThrow()) {
                        Box(
                            modifier = Modifier
                                .width(230.dp)
                                .wrapContentHeight()
                        ) {
                            AssetItem(it) { asset ->
                                assetViewModel.setCurrentAsset(asset)
                                eventHandler.postNavEvent(NavEvent.Action(NavTarget.AssetDetails))
                            }
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
                ) {
                    eventHandler.postNavEvent(NavEvent.Action(NavTarget.Transaction))
                }
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
private fun Banner(onCreateNFT: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, Gray4),
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
                text = buildAnnotatedString {
                    append("Start Creating your ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("NFTs")
                    }
                    append(" Today")
                },
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
                onClick = { onCreateNFT() }) {
                Text(
                    text = stringResource(id = R.string.create_nft),
                    color = Color.White
                )
            }
        }
    }
}
