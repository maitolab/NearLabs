package org.builds.nearlabs.domain.model.asset

sealed class AssetType(val name: String) {
    object DigitalArt : AssetType("Digital Art")
    object Collectibles : AssetType("Collectibles")
    object Music : AssetType("Music")
    object VirturalWorlds : AssetType("Virtual Worlds")
}
