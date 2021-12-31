package org.builds.nearlabs.domain.model.asset

import org.builds.nearlabs.common.shorten

data class AssetInfo(
    val tokenId: String,
    val contract: String
) {
    fun getShortenContract() = contract.shorten()
}