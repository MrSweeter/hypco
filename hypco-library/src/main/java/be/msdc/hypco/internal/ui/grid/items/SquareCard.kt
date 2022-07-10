package be.msdc.hypco.internal.ui.grid.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import be.msdc.hypco.internal.ui.widget.nodeClickable
import be.msdc.hypco.internal.utils.ribbon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SquareCard(
    item: NodeItem,
    selectedItem: ItemSelector?
) {
    val image = item.itemImage ?: ItemImage.DEFAULT_IMG

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(getCardBackgroundColor())
                .nodeClickable(item, selectedItem)
                .ribbon(item.state, 6)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = image.drawable),
                contentDescription = image.name,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                alignment = CenterStart
            )
            Column(
                modifier = Modifier
                    .weight(0.5f),
                verticalArrangement = Arrangement.spacedBy(6.dp, CenterVertically),
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.subtitle ?: "",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview("SquareCard_P", device = Devices.PHONE)
@Preview("SquareCard_T", device = Devices.TABLET)
@Preview("SquareCard_D", device = Devices.DESKTOP)
@Composable
private fun SquareCardPreview() {
    SquareCard(
        item = ItemBuilder.preview().apply { state = ItemState.WARNING },
        selectedItem = { _, _ -> }
    )
}