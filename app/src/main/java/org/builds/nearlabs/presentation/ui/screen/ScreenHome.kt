package org.builds.nearlabs.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import org.builds.nearlabs.R
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.domain.DataPool
import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.domain.model.transaction.Transaction
import org.builds.nearlabs.presentation.ui.theme.Blue
import org.builds.nearlabs.presentation.ui.theme.Gray1
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
                Header(header = stringResource(id = R.string.my_nft))
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(popularAssets.value.takeValueOrThrow()) {
                        AssetPopularItem(it)
                    }
                }
            }
        }

        if (recentTransactions.value is ResultWrapper.Success) {
            item {
                Header(header = stringResource(id = R.string.recent_transaction))
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
private fun UserInfo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .border(1.dp, Gray2, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),

            ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_user_avatar),
                contentDescription = "avatar"
            )

            Text(
                text = "john.near",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
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

@Composable
private fun Header(header: String) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (title, seeAll) = createRefs()
        Text(
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(seeAll.start)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            text = header,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(
            modifier = Modifier.constrainAs(seeAll) {
                top.linkTo(title.top)
                end.linkTo(parent.end)
                bottom.linkTo(title.bottom)
            },
            text = stringResource(id = R.string.see_all),
            fontWeight = FontWeight.Normal,
            color = Blue
        )
    }
}

@Composable
private fun AssetPopularItem(asset: Asset) {
    Card(
        modifier = Modifier
            .width(230.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, Gray2)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (image, title, order, type) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                    }
                    .height(120.dp),
                painter = painterResource(id = asset.getImageResource()),
                contentDescription = "asset image",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(image.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                text = asset.name,
                color = Color.Black,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.constrainAs(order) {
                    top.linkTo(title.bottom, margin = 12.dp)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    width = Dimension.fillToConstraints
                },
                text = "#${asset.id}",
                color = Gray1
            )

            Text(
                modifier = Modifier
                    .constrainAs(type) {
                        top.linkTo(image.top, margin = 16.dp)
                        start.linkTo(title.start)
                        width = Dimension.wrapContent
                    }
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                text = asset.type.name,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun TransactionItem(transaction: Transaction) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (icon, order, desc, timestamp) = createRefs()
        Icon(
            modifier = Modifier
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(50.dp)
                .border(1.dp, Gray2, CircleShape)
                .padding(16.dp),
            imageVector = ImageVector.vectorResource(id = if (transaction.isIncoming()) R.drawable.ic_receive else R.drawable.ic_sent),
            contentDescription = "icon"
        )

        Text(
            modifier = Modifier.constrainAs(order) {
                start.linkTo(icon.end, margin = 12.dp)
                top.linkTo(parent.top, margin = 8.dp)
                end.linkTo(timestamp.start)
                bottom.linkTo(desc.top)
                width = Dimension.fillToConstraints
            },
            text = "#${transaction.id}",
            color = Blue,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.constrainAs(desc) {
                start.linkTo(order.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 8.dp)
                top.linkTo(order.bottom, margin = 2.dp)
                width = Dimension.fillToConstraints
            },
            text = when {
                transaction.isIncoming() -> buildAnnotatedString {
                    append(stringResource(id = R.string.receive_from))
                    append(" ")
                    withStyle(SpanStyle(Blue)) {
                        append(transaction.sender.address)
                    }
                }
                else -> buildAnnotatedString {
                    append(stringResource(id = R.string.sent_to))
                    append(" ")
                    withStyle(SpanStyle(Blue)) {
                        append(transaction.receiver.name)
                    }
                }
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 13.sp
        )

        Text(
            modifier = Modifier.constrainAs(timestamp) {
                end.linkTo(parent.end)
                top.linkTo(order.top)
                bottom.linkTo(order.bottom)
            },
            text = "3 days ago",
            color = Gray1
        )
    }
}