package be.msdc.hypco.internal.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import be.msdc.hypco.api.model.ItemType
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.grid.GridBodyView
import be.msdc.hypco.internal.ui.list.ListBodyView

@Composable
internal fun CombineBodyView(
    item: NodeItem,
    selectedItem: ItemSelector
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)) {
        GridBodyView(children = item.getChildren(ItemType.CollectionType.GRID), selectedItem = selectedItem)
        ListBodyView(children = item.getChildren(ItemType.CollectionType.LIST), selectedItem = selectedItem)
    }
}