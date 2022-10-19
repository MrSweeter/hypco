package be.msdc.hypco.internal.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.ItemType
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.list.items.ProgressCard
import be.msdc.hypco.internal.ui.list.items.SimpleCard
import be.msdc.hypco.internal.ui.list.items.SimpleLink
import be.msdc.hypco.internal.ui.list.items.SimpleText

@Composable
internal fun ListBodyView(
    children: Set<NodeItem>,
    selectedItem: ItemSelector
) {
    LazyColumn {
        for (child in children) {
            item {
                when (child.type) {
                    ItemType.PROGRESS_CARD -> ProgressCard(item = child, selectedItem = selectedItem)
                    ItemType.SIMPLE_CARD -> SimpleCard(item = child, selectedItem = selectedItem)
                    ItemType.SIMPLE_LINK -> SimpleLink(item = child, selectedItem = selectedItem)
                    ItemType.SIMPLE_TEXT,
                    ItemType.DEFAULT -> SimpleText(item = child, selectedItem = selectedItem)
                    else -> SimpleText(item = child, selectedItem = selectedItem)
                }
            }
        }
    }
}

@Preview("ListBodyView_P", device = Devices.PHONE)
@Preview("ListBodyView_T", device = Devices.TABLET)
@Preview("ListBodyView_D", device = Devices.DESKTOP)
@Composable
private fun ListBodyViewPreview() {
    ListBodyView(ItemBuilder.tree().children) { _, _ -> }
}