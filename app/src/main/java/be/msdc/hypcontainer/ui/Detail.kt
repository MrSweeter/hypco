package be.msdc.hypcontainer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.msdc.hypcontainer.model.NodeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(
    item: NodeItem,
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(item = item, navigateUp = navigateUp)
        },
    ) { pad ->
        Column(modifier = Modifier.padding(vertical = pad.calculateTopPadding())) {
            Text(text = "Parent ${item.parent?.id}")
            Text(text = "Title ${(item as? NodeItem)?.title}")
            Text(text = "Type ${item::class.java.simpleName}")
            Button(
                onClick = navigateUp
            ) {
                Text(text = "Back")
            }
        }
    }
}