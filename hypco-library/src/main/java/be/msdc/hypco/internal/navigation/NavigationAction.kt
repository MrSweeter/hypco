package be.msdc.hypco.internal.navigation

import androidx.navigation.NavHostController
import be.msdc.hypco.api.model.NodeItem

internal fun interface ItemSelector   {
    fun select(item: NodeItem, showDetails: Boolean)
}

internal class NavigationAction(navController: NavHostController) {
    val selectItem: ItemSelector = ItemSelector { item, showDetails ->
        if (showDetails) {
            navController.navigate("${AppDestination.ITEM_ROUTE}/${item.id}/details")
        } else {
            navController.navigate("${AppDestination.ITEM_ROUTE}/${item.id}")
        }
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}