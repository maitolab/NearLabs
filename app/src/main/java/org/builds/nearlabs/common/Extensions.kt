package org.builds.nearlabs.common

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> ViewModel.resultInState(
    mutableState: MutableState<ResultWrapper<T>>,
    callback: suspend () -> ResultWrapper<T>
) {
    viewModelScope.launch {
        with(this@resultInState) {
            mutableState.value = ResultWrapper.Loading
            mutableState.value = callback()
        }
    }
}

suspend fun <T> safeCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    dataOperation: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(dataOperation.invoke())
        } catch (throwable: Throwable) {
            ResultWrapper.GenericError(throwable)
        }
    }
}