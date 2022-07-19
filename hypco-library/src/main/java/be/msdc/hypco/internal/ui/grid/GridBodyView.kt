package be.msdc.hypco.internal.ui.grid

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.ItemType
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.grid.items.SquareCard
import be.msdc.hypco.internal.ui.widget.AppWindowInfo
import be.msdc.hypco.internal.ui.widget.rememberWindowInfo

@Composable
internal fun GridBodyView(
    children: Set<NodeItem>,
    selectedItem: ItemSelector
) {
    val columnCount = when (rememberWindowInfo().screenWidthInfo) {
        AppWindowInfo.WindowType.Compact -> 2
        AppWindowInfo.WindowType.Medium -> 4
        AppWindowInfo.WindowType.Expanded -> 6
    }
    LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
        for (child in children) {
            item {
                when (child.type) {
                    ItemType.SQUARE_CARD -> SquareCard(item = child, selectedItem = selectedItem)
                    else -> SquareCard(item = child, selectedItem = selectedItem)
                }
            }
        }
    }
}

@Preview("GridBodyView_P", device = Devices.PHONE)
@Preview("GridBodyView_T", device = Devices.TABLET)
@Preview("GridBodyView_D", device = Devices.DESKTOP)
@Composable
private fun GridBodyViewPreview() {
    GridBodyView(ItemBuilder.tree().children) { _, _ -> }
}