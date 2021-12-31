package org.builds.nearlabs.domain.model.transaction

import org.builds.nearlabs.common.shorten

data class TransactionAddress(
    val address: String,
    val name: String
) {
    fun getShortenAddress() = address.shorten()
}