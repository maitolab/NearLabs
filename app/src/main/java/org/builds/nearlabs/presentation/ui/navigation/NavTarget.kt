package org.builds.nearlabs.presentation.ui.navigation

sealed class NavTarget(val route: String) {
    object Authentication : NavTarget("authentication")
    object Home : NavTarget("home")
    object Assets: NavTarget("assets")
    object AssetDetails: NavTarget("assets/details")
    object NFT : NavTarget("nft")
    object Transaction : NavTarget("transaction")
}