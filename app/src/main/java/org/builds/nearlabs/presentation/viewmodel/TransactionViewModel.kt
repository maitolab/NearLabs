package org.builds.nearlabs.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.common.resultInState
import org.builds.nearlabs.domain.Repository
import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.domain.model.transaction.Transaction
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getRecentTransactions(state: MutableState<ResultWrapper<List<Transaction>>>) = resultInState(state) {
        repository.getRecentTransactions()
    }

    fun getHistoryTransactions(state: MutableState<ResultWrapper<List<Transaction>>>) = resultInState(state) {
        repository.getHistoryTransactions()
    }

}