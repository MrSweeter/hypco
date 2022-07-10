package be.msdc.hypco.internal.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.Modifier
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector

@OptIn(ExperimentalFoundationApi::class)
internal fun Modifier.nodeClickable(item: NodeItem, onClick: ItemSelector?): Modifier {
    onClick?.let { return combinedClickable(
        onClick = if (item.isClickable) {
            { onClick.select(item, false) }
        } else {
            {  }
        },
        onLongClick = if (item.isLongClickable) {
            { onClick.select(item, true) }
        } else {
            {  }
        }
    )}
    return this
}