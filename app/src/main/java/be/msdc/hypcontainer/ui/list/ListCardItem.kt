package be.msdc.hypcontainer.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.msdc.hypcontainer.model.NodeItem
import be.msdc.hypcontainer.model.ItemType
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCardItem(
    item: NodeItem,
    selectedItem: (NodeItem) -> Unit
) {
    Card(
        modifier = Modifier
            .focusable(true)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { selectedItem(item) },
        elevation = CardDefaults.cardElevation(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
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
    }
}

@Preview("ListCardItem")
@Composable
fun ListCardItemPreview() {
    ListCardItem(
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