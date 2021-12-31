package org.builds.nearlabs.domain.model.asset

import org.builds.nearlabs.R
import kotlin.random.Random

data class Asset(
     val id: Long,
     val name: String,
     val type: AssetType,
     val image: String,
     val author: Author,
     val description: String,
     val info: AssetInfo
) {
     fun identifier() = "#$id"
     fun getImageResource() = if (Random.nextBoolean()) R.drawable.asset1 else R.drawable.asset2
}

