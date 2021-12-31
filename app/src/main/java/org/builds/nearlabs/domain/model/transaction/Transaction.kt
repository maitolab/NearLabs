package org.builds.nearlabs.domain.model.transaction

import org.threeten.bp.LocalDateTime


data class Transaction(
    val id: Long,
    val sender: TransactionAddress,
    val receiver: TransactionAddress,
    val direction: TransactionDirection,
    val timestamp: LocalDateTime
) {
    fun isIncoming() = direction == TransactionDirection.Incoming
}