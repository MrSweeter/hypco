package be.msdc.hypco.internal.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import be.msdc.hypco.api.model.ItemState

internal fun ItemState.color(): Color = when (this) {
    ItemState.INFO -> Color.Blue
    ItemState.OK -> Color.Green
    ItemState.WARNING -> Color.Yellow
    ItemState.ERROR -> Color.Red
    ItemState.UNKNOWN -> Color.Gray
    ItemState.CONNECTED -> Color.Green
    ItemState.PENDING -> Color.Magenta
    ItemState.DISCONNECTED -> Color.Gray
    ItemState.AVAILABLE -> Color.Green
    ItemState.UNAVAILABLE -> Color.Red
}

internal fun ItemState.showBadge(): Boolean = when (this) {
    ItemState.INFO -> false
    ItemState.OK -> false
    ItemState.WARNING -> true
    ItemState.ERROR -> true
    ItemState.UNKNOWN -> false
    ItemState.CONNECTED -> true
    ItemState.PENDING -> true
    ItemState.DISCONNECTED -> false
    ItemState.AVAILABLE -> false
    ItemState.UNAVAILABLE -> true
}

internal fun Modifier.ribbon(state: ItemState, width: Int): Modifier = this.then(
    Modifier.drawBehind {
        val strokeWidth = width * density
        val x = strokeWidth / 2

        drawLine(
            state.color().copy(alpha = if (state.showBadge()) 1f else 0f),
            Offset(x, 0f),
            Offset(x, size.height),
            strokeWidth
        )
    }
)