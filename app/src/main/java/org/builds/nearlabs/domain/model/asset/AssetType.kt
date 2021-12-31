package org.builds.nearlabs.domain.model.asset

sealed class AssetType(val name: String) {
    object DigitalArt : AssetType("Digital Art")
}
