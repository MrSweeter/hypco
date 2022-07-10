package be.msdc.hypco.internal.ui.list.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SimpleCard(
    item: NodeItem,
    selectedItem: ItemSelector
) {
    val image = item.itemImage ?: ItemImage.DEFAULT_IMG

    Card(
        modifier = Modifier
            .height(if (item.subtitle.isNullOrBlank()) 85.dp else 100.dp)
            .focusable(true)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(getCardBackgroundColor())
                .ribbon(item.state, 4)
                .nodeClickable(item, selectedItem)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image.drawable),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
                alignment = CenterStart
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp, CenterVertically),
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
}

@Preview("SimpleCard_P", device = Devices.PHONE)
@Preview("SimpleCard_T", device = Devices.TABLET)
@Preview("SimpleCard_D", device = Devices.DESKTOP)
@Composable
private fun SimpleCardPreview() {
    SimpleCard(
        item = ItemBuilder.preview().apply { state = ItemState.ERROR },
        selectedItem = { _, _ -> }
    )
}