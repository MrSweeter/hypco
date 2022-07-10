package be.msdc.hypco.internal.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.detail.DetailBodyView
import be.msdc.hypco.internal.ui.grid.GridBodyView
import be.msdc.hypco.internal.ui.list.ListBodyView
import be.msdc.hypco.internal.ui.widget.ItemScaffold

@Composable
internal fun ItemScreen(
    item: NodeItem,
    navController: NavController,
    selectedItem: ItemSelector,
    navigateUp: () -> Unit
) {
    ItemScaffold(item = item, navController = navController, navigateUp = navigateUp) {
        when {
            item.isSplit -> CombineBodyView(item = item, selectedItem = selectedItem)
            item.isListParent -> ListBodyView(children = item.children, selectedItem = selectedItem)
            item.isGridParent -> GridBodyView(children = item.children, selectedItem = selectedItem)
            else -> DetailBodyView(item = item, selectedItem = selectedItem)
        }
    }
}