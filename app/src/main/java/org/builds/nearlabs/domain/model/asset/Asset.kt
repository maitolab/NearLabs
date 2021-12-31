package org.builds.nearlabs.domain.model.asset

import org.builds.nearlabs.R
import kotlin.random.Random

object DummyImageUrl {
     const val IMAGE_1 = "image_1"
     const val IMAGE_2 = "image_2"
}

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

     fun getImageResource() = when (image) {
          DummyImageUrl.IMAGE_1 -> R.drawable.asset1
          else -> R.drawable.asset2
     }
}

