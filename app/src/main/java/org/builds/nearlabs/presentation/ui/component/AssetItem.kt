package org.builds.nearlabs.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import org.builds.nearlabs.domain.model.asset.Asset
import org.builds.nearlabs.presentation.ui.theme.Gray1
import org.builds.nearlabs.presentation.ui.theme.Gray2


@Composable
fun AssetItem(asset: Asset) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, Gray2)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (image, title, order, type) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                    }
                    .height(160.dp),
                painter = painterResource(id = asset.getImageResource()),
                contentDescription = "asset image",
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(image.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                text = asset.name,
                color = Color.Black,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.constrainAs(order) {
                    top.linkTo(title.bottom, margin = 12.dp)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    width = Dimension.fillToConstraints
                },
                text = "#${asset.id}",
                color = Gray1
            )

            Text(
                modifier = Modifier
                    .constrainAs(type) {
                        top.linkTo(image.top, margin = 16.dp)
                        start.linkTo(title.start)
                        width = Dimension.wrapContent
                    }
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                text = asset.type.name,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
