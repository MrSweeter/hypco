package be.msdc.hypco.internal.ui.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.ItemContentType
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.navigation.ItemSelector
import be.msdc.hypco.internal.ui.screen.CombineBodyView
import be.msdc.hypco.internal.ui.widget.StateTextBadge

@Composable
internal fun DetailBodyView(
    item: NodeItem,
    selectedItem: ItemSelector
) {
    val context =  LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var tabIndex by remember { mutableStateOf(0)}
    val tabData = listOf(
        "Info",
        "Content"
    )

    Column(modifier = Modifier
        .fillMaxHeight()) {
        TabRow(selectedTabIndex = tabIndex) {
            Tab(selected = tabIndex == 0, onClick = { tabIndex = 0}, text = { Text(text = tabData[0]) })
            Tab(selected = tabIndex == 1, onClick = { tabIndex = 1}, text = { Text(text = tabData[1]) })
        }

        when (tabIndex) {
            0 -> {
                Column(modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    StateTextBadge(state = item.state)

                    Text(text = "ID: ${item.id}")
                    Text(text = "Title: ${item.title}")
                    Text(text = "Subtitle: ${item.subtitle}")
                    Text(text = "Description: ${item.description}")
                    Text(text = "Image: ${item.itemImage}")
                    Text(text = "Date: ${item.date.toLocaleString()}")
                    Text(text = "Type: ${item.type}")
                    Text(text = "ContentType: ${item.contentType}")
                    Text(text = "Parent ID: ${item.parentID}")
                    Text(text = "Children: ${item.children.size}")
                }
            }
            1 -> {
                if (item.contentType != ItemContentType.NOTHING && !item.content.isNullOrEmpty())    {
                    Column(modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .clickable {
                            clipboardManager.setText(AnnotatedString(item.getFormattedContent()))
                            Toast
                                .makeText(context, "Content copied to clipboard", Toast.LENGTH_LONG)
                                .show()
                        }) {
                            Text(text = item.getFormattedContent())
                        }
                } else {
                    Column {
                        Text(text = "No content but: ${item.children.size} children")
                        CombineBodyView(item = item, selectedItem = selectedItem)
                    }
                }
            }
            else -> {
                Text(text = "Unknown tab")
            }
        }
    }
}

@Preview("DetailBodyView_P", device = Devices.PHONE)
@Preview("DetailBodyView_T", device = Devices.TABLET)
@Preview("DetailBodyView_D", device = Devices.DESKTOP)
@Composable
private fun DetailBodyViewPreview() {
    DetailBodyView(ItemBuilder.preview()) { _, _ ->}
}