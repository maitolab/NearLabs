package org.builds.nearlabs.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.common.resultInState
import org.builds.nearlabs.domain.Repository
import org.builds.nearlabs.domain.model.asset.Asset
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var selectedAsset = mutableStateOf<Asset?>(null)

    fun getPopularAssets(state: MutableState<ResultWrapper<List<Asset>>>) = resultInState(state) {
        repository.getPopularAssets()
    }

    fun getAllAssets(state: MutableState<ResultWrapper<List<Asset>>>) = resultInState(state) {
        repository.getAllAssets()
    }

    fun setCurrentAsset(asset: Asset) {
        selectedAsset.value = asset
    }
}