package org.builds.nearlabs.domain.model.transaction

import org.ocpsoft.prettytime.PrettyTime
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.*
import kotlin.math.absoluteValue


data class Transaction(
    val id: Long,
    val sender: TransactionAddress,
    val receiver: TransactionAddress,
    val direction: TransactionDirection,
    val timestamp: LocalDateTime
) {
    fun identifier() = "#$id"
    fun isIncoming() = direction == TransactionDirection.Incoming
    fun getPrettyTime() : String {
        return PrettyTime().format(
            Date(timestamp.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli())
        )
    }
}