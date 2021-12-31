package org.builds.nearlabs.domain

import kotlinx.coroutines.flow.Flow
import org.builds.nearlabs.common.ResultWrapper
import org.builds.nearlabs.common.safeCall
import org.builds.nearlabs.domain.model.asset.Asset

class Repository {

    suspend fun getPopularAssets() = safeCall {
        DataPool.assets().take(10)
    }

    suspend fun getAllAssets() = safeCall {
        DataPool.assets()
    }

    suspend fun getRecentTransactions() = safeCall {
        DataPool.transactions().take(10)
    }

    suspend fun getHistoryTransactions() = safeCall {
        DataPool.transactions()
    }
}