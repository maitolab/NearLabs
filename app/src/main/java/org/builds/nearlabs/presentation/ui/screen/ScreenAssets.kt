package org.builds.nearlabs.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.builds.nearlabs.R
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.presentation.ui.component.Header
import org.builds.nearlabs.presentation.ui.component.AssetItem
import org.builds.nearlabs.presentation.ui.event.NavEvent
import org.builds.nearlabs.presentation.ui.event.initEventHandler
import org.builds.nearlabs.presentation.ui.navigation.NavTarget
import org.builds.nearlabs.presentation.ui.component.UserInfo
import org.builds.nearlabs.presentation.viewmodel.initAssetViewModel

@Composable
fun ScreenAssets() {
    val eventHandler = initEventHandler()
    val assetViewModel = initAssetViewModel()

    val assets: MutableState<ResultWrapper<List<Asset>>> = remember {
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

        if (assets.value is ResultWrapper.Success) {
            item {
                Header(
                    header = stringResource(id = R.string.my_nft),
                    action = stringResource(id = R.string.create_nft)
                ) {
                    eventHandler.postNavEvent(NavEvent.Action(NavTarget.CreateNFT))
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    assets.value.takeValueOrThrow().forEach { asset ->
                        AssetItem(asset = asset) {
                            assetViewModel.setCurrentAsset(asset)
                            eventHandler.postNavEvent(
                                NavEvent.Action(NavTarget.AssetDetails)
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = "fetchData") {
        assetViewModel.getAllAssets(assets)
    }
}