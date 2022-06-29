package be.msdc.hypcontainer.ui

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.msdc.hypcontainer.model.NodeItem

/**
 * TopAppBar for the Home screen
 */
@Composable
fun AppTopBar(
    item: NodeItem,
    navigateUp: () -> Unit
) {
    Log.println(Log.ASSERT, "LOGME", "${item.id} - ${item.parent}")
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        navigationIcon = {
            if (item.parent != null)    {
                IconButton(
                    onClick = navigateUp,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.secondary),
                    content = { Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "") },
                    modifier = Modifier.padding(end = 16.dp).focusable(true)
                )
            }
        },
        actions = {

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}