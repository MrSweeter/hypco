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
import be.msdc.hypco.api.model.ItemState
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.utils.getCardBackgroundColor
import be.msdc.hypco.internal.ui.widget.SubtitleText
import be.msdc.hypco.internal.ui.widget.nodeClickable
import be.msdc.hypco.internal.utils.ribbon
import be.msdc.hypco.R
import be.msdc.hypco.api.model.ItemType

@Composable
internal fun SimpleLink(
    item: NodeItem,
    selectedItem: ItemSelector
) {
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_open_in_browser_24),
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

@Preview("SimpleLink_P", device = Devices.PHONE)
@Preview("SimpleLink_T", device = Devices.TABLET)
@Preview("SimpleLink_D", device = Devices.DESKTOP)
@Composable
private fun SimpleLinkPreview() {
    SimpleLink(
        item = ItemBuilder.preview().apply { state = ItemState.ERROR; type = ItemType.SIMPLE_LINK },
        selectedItem = { _, _ -> }
    )
}