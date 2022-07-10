package be.msdc.hypco.internal.ui.list.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
internal fun ProgressCard(
    item: NodeItem,
    selectedItem: ItemSelector
) {
    val image = item.itemImage ?: ItemImage.DEFAULT_IMG

    Card(
        modifier = Modifier
            .height(150.dp)
            .focusable(true)
            .padding(vertical = 8.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(),
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(getCardBackgroundColor())
                .ribbon(item.state, 6)
                .nodeClickable(item, selectedItem)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = image.drawable),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
                alignment = TopStart
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp, CenterVertically),
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
                SubtitleText(text = item.subtitle, paddingBottom = 20.dp)

                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.progressText ?: "",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodySmall,
                )
                if (item.maxProgress != 0f)  {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        trackColor = MaterialTheme.colorScheme.onSurface,
                        color = MaterialTheme.colorScheme.primary,
                        progress = (item.progress / item.maxProgress),
                    )
                } else {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        trackColor = MaterialTheme.colorScheme.onSurface,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview("ProgressCard_P", device = Devices.PHONE)
@Preview("ProgressCard_T", device = Devices.TABLET)
@Preview("ProgressCard_D", device = Devices.DESKTOP)
@Composable
private fun ProgressCardPreview() {
    ProgressCard(
        item = ItemBuilder.preview().apply { state = ItemState.ERROR },
        selectedItem = { _, _ -> }
    )
}