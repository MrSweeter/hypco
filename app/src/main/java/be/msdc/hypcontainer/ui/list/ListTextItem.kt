package be.msdc.hypcontainer.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.msdc.hypcontainer.model.NodeItem
import be.msdc.hypcontainer.model.ItemType
import java.util.*

@Composable
fun ListTextItem(
    item: NodeItem,
    selectedItem: (NodeItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .focusable(true)
            .padding(horizontal = 16.dp)
            .height(72.dp)
            .fillMaxWidth()
            .clickable { selectedItem(item) }
            .wrapContentHeight()
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
        )
        if (!item.subtitle.isNullOrBlank())  {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = item.subtitle ?: "",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
    Divider(color = MaterialTheme.colorScheme.primary)
}

@Preview("ListTextItem")
@Composable
fun ListTextItemPreview() {
    ListTextItem(
        item = NodeItem(
            id = UUID.randomUUID().toString(),
            title = "Title",
            subtitle = "Subtitle",
            description = "",
            date = Date(),
            type = ItemType.DEFAULT
        ),
        selectedItem = { }
    )
}