package org.builds.nearlabs.presentation.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.builds.nearlabs.presentation.ui.event.EventHandler

@Composable
fun initAssetViewModel() : AssetViewModel {
    val context = LocalContext.current as ComponentActivity
    return hiltViewModel(context)
}

@Composable
fun initTransactionViewModel() : TransactionViewModel {
    val context = LocalContext.current as ComponentActivity
    return hiltViewModel(context)
}