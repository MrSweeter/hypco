package be.msdc.hypcontainer.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import be.msdc.hypcontainer.model.*

@Composable
fun Item(
    item: NodeItem?,
    selectedItem: (NodeItem) -> Unit,
    navigateUp: () -> Unit
)   {
    when (item) {
        is NodeItem -> {
            if (item.isParent) be.msdc.hypcontainer.ui.list.List(item = item, selectedItem = selectedItem, navigateUp)
            else Detail(item = item, navigateUp)
        }
        else -> {
            Text("Invalid item")
        }
    }
}