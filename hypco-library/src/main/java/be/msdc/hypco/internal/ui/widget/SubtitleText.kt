package be.msdc.hypco.internal.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp

@Composable
internal fun SubtitleText(text: String?, paddingBottom: Dp) {
    Text(
        modifier = Modifier.padding(bottom = paddingBottom),
        maxLines = 2,
        softWrap = true,
        overflow = TextOverflow.Ellipsis,
        text = text ?: "",
        style = MaterialTheme.typography.bodySmall,
    )
}