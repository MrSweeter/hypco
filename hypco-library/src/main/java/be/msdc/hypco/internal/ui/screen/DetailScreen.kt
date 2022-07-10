package be.msdc.hypco.internal.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.detail.DetailBodyView
import be.msdc.hypco.internal.ui.widget.ItemScaffold

@Composable
internal fun DetailScreen(
    item: NodeItem,
    navController: NavController,
    navigateUp: () -> Unit,
    selectedItem: ItemSelector
) {
    ItemScaffold(item = item, navController = navController, navigateUp = navigateUp) {
        DetailBodyView(item = item, selectedItem = selectedItem)
    }
}