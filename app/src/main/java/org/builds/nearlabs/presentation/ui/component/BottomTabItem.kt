package org.builds.nearlabs.presentation.ui.component

import androidx.annotation.DrawableRes
import org.builds.nearlabs.R
import org.builds.nearlabs.presentation.ui.navigation.NavTarget

enum class BottomTabItem(
    @DrawableRes val icon: Int,
    val route: String
) {
    HOME(R.drawable.ic_home, NavTarget.Home.route),
    NFT(R.drawable.ic_nft, NavTarget.NFT.route),
    TRANSACTION(R.drawable.ic_transaction, NavTarget.Transaction.route)
}