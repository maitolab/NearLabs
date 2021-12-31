package org.builds.nearlabs.domain.model.transaction

sealed class TransactionDirection {
    object Incoming: TransactionDirection()
    object Outgoing: TransactionDirection()
}