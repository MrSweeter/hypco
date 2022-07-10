package be.msdc.hypco.internal.ui.widget

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.msdc.hypco.api.model.ItemState
import be.msdc.hypco.internal.utils.color

@Composable
internal fun StateTextBadge(state: ItemState) {
    val color: Color = state.color()
    Text(
        modifier = Modifier
            .background(color.copy(alpha = 0.5f), shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .alpha(0.75f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        text = state.name.uppercase(),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Preview("StateTextBadge", uiMode = UI_MODE_NIGHT_YES)
@Preview("StateTextBadge", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun StateTextBadgePreview() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        StateTextBadge(ItemState.WARNING)
        StateTextBadge(ItemState.ERROR)
        StateTextBadge(ItemState.UNKNOWN)
    }
}