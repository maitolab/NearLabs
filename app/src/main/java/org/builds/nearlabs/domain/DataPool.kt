package org.builds.nearlabs.domain

import org.builds.nearlabs.domain.model.asset.*
import org.builds.nearlabs.domain.model.transaction.Transaction
import org.builds.nearlabs.domain.model.transaction.TransactionAddress
import org.builds.nearlabs.domain.model.transaction.TransactionDirection
import org.threeten.bp.LocalDateTime
import kotlin.random.Random

object DataPool {

    private fun assetNames() = listOf(
        "Async Blueprints",
        "Party Bear",
        "Domains.Kred",
        "Decentraland Names"
    )

    fun assets(): List<Asset> {
        val names = assetNames()
        return (17720L..17820L).map {
            val assetName = names[it.toInt() % names.size]
            Asset(
                id = it,
                name = assetName,
                type = when (it % 4) {
                    0L -> AssetType.DigitalArt
                    1L -> AssetType.Collectibles
                    2L -> AssetType.Music
                    3L -> AssetType.VirturalWorlds
                    else -> AssetType.DigitalArt
                },
                image = if (it % 2 == 0L) DummyImageUrl.IMAGE_1 else DummyImageUrl.IMAGE_2,
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