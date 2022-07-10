package be.msdc.hypco.internal.ui.list.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.ItemImage
import be.msdc.hypco.api.model.ItemState
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.utils.getCardBackgroundColor
import be.msdc.hypco.internal.ui.widget.SubtitleText
import be.msdc.hypco.internal.ui.widget.nodeClickable
import be.msdc.hypco.internal.utils.ribbon

@Composable
internal fun SimpleText(
    item: NodeItem,
    selectedItem: ItemSelector
) {
    val image = item.itemImage ?: ItemImage.DEFAULT_IMG
    val iconBackgroundColor = getCardBackgroundColor()

    Divider(color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.25f))
    Row(
        modifier = Modifier
            .height(75.dp)
            .nodeClickable(item, selectedItem)
            .ribbon(item.state, 3)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image.drawable),
            contentDescription = "",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(start = 8.dp, end = 24.dp)
                .drawBehind {
                    drawCircle(
                        color = iconBackgroundColor,
                        radius = this.size.maxDimension
                    )
                },
            alignment = Alignment.CenterStart
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        ) {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = item.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
            )
            SubtitleText(text = item.subtitle, paddingBottom = 0.dp)
        }
    }
}

@Preview("SimpleText_P", device = Devices.PHONE)
@Preview("SimpleText_T", device = Devices.TABLET)
@Preview("SimpleText_D", device = Devices.DESKTOP)
@Composable
private fun SimpleTextPreview() {
    SimpleText(
        item = ItemBuilder.preview().apply { state = ItemState.ERROR },
        selectedItem = { _, _ -> }
    )
}