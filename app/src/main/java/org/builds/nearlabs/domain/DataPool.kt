package org.builds.nearlabs.domain

import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.domain.model.asset.AssetInfo
import org.builds.nearlabs.domain.model.asset.AssetType
import org.builds.nearlabs.domain.model.asset.Author
import org.builds.nearlabs.domain.model.transaction.Transaction
import org.builds.nearlabs.domain.model.transaction.TransactionAddress
import org.builds.nearlabs.domain.model.transaction.TransactionDirection
import org.threeten.bp.LocalDateTime
import kotlin.random.Random

object DataPool {

    private fun assetNames() = listOf(
        "Nature Divas",
        "Dear Julia",
        "Vecotry Illustration",
        "Nature Illustration"
    )

    fun asset() = Asset(
        id = 15346,
        name = "Vecotry Illustration",
        type = AssetType.DigitalArt,
        image = "",
        author = Author(
            name = "john_doe",
            image = ""
        ),
        info = AssetInfo(
            tokenId = "38943",
            contract = "0xa6f79B60359f141df90A0C745125B131cAAfFD12"
        ),
        description = "Having returned home to Rathleigh House near Macroom, Cork, Ireland, the hot-tempered Art became involved in a feud with a protestant landowner and magistrate, "
    )

    fun assets(): List<Asset> {
        val names = assetNames()
        return (1720L..1820L).map {
            val assetName = names[it.toInt() % names.size]
            Asset(
                id = it,
                name = assetName,
                type = AssetType.DigitalArt,
                image = "",
                author = Author(
                    name = "john_doe",
                    image = ""
                ),
                info = AssetInfo(
                    tokenId = "38943",
                    contract = "0xa6f79B60359f141df90A0C745125B131cAAfFD12"
                ),
                description = "Having returned home to Rathleigh House near Macroom, Cork, Ireland, the hot-tempered Art became involved in a feud with a protestant landowner and magistrate, "
            )
        }
    }

    fun transactions(): List<Transaction> {
        return (1720L..1820L).map {
            Transaction(
                id = it,
                sender = TransactionAddress(
                    name = "michael.near",
                    address = "0xa6f79B60359f141df90A0C745125B131cAAfFD12"
                ),
                receiver = TransactionAddress(
                    name = "michael.near",
                    address = "0xa6f79B60359f141df90A0C745125B131cAAfFD12"
                ),
                direction = if (Random.nextBoolean()) TransactionDirection.Incoming else TransactionDirection.Outgoing,
                timestamp = LocalDateTime.now().minusDays(3)
            )
        }
    }
}