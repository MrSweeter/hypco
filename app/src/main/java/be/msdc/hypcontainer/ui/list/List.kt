package be.msdc.hypcontainer.ui.list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.msdc.hypcontainer.model.*
import be.msdc.hypcontainer.ui.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun List(
    item: NodeItem,
    selectedItem: (NodeItem) -> Unit,
    navigateUp: () -> Unit
)   {
    Scaffold(
        topBar = {
            AppTopBar(item = item, navigateUp = navigateUp)
        },
    ) { pad ->
        Column(modifier = Modifier.padding(vertical = pad.calculateTopPadding())) {
            ListBody(item = item, selectedItem = selectedItem)
        }
    }
}

@Composable
private fun ListBody(
    item: NodeItem,
    selectedItem: (NodeItem) -> Unit
) {
    LazyColumn {
        for (child in item.children) {
            item {
                Log.println(Log.ASSERT, "LOGME", "child: ${child.type}")
                when {
                    child.type == ItemType.CARD -> ListCardItem(item = child, selectedItem = selectedItem)
                    child.type == ItemType.DEFAULT && child.image != null -> ListImageItem(item = child, selectedItem = selectedItem)
                    else -> ListTextItem(item = child, selectedItem = selectedItem)
                }
            }
        }
    }
}