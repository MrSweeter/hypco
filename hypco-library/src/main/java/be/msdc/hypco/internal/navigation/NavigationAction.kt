package be.msdc.hypco.internal.navigation

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavHostController
import be.msdc.hypco.api.model.ItemType
import be.msdc.hypco.api.model.NodeItem

internal fun interface ItemSelector   {
    fun select(item: NodeItem, showDetails: Boolean)
}

internal class NavigationAction(navController: NavHostController) {
    val selectItem: ItemSelector = ItemSelector { item, showDetails ->
        if (item.type == ItemType.SIMPLE_LINK)  {
            if (item.link.isNullOrBlank())  {
                Toast.makeText(navController.context, "No link provided", Toast.LENGTH_LONG).show()
                return@ItemSelector
            }

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link!!))

            if (intent.resolveActivity(navController.context.packageManager) != null) {
                navController.context.startActivity(intent)
                return@ItemSelector
            } else {
                Toast.makeText(navController.context, "Nothing to open", Toast.LENGTH_SHORT).show()
            }
        }

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