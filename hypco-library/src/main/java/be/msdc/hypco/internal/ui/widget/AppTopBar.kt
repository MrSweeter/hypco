package be.msdc.hypco.internal.ui.widget

import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.msdc.hypco.R
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.NodeItem

@Composable
internal fun AppTopBar(
    item: NodeItem,
    canBack: Boolean,
    navigateUp: () -> Unit
) {
    val iconColor = MaterialTheme.colorScheme.secondary
    var displayMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val moreRotation by animateFloatAsState(
        targetValue = if (displayMenu) 90f else 0f,
        animationSpec = tween(durationMillis = 750)
    )

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        navigationIcon = {
            if (canBack) {
                IconButton(
                    onClick = navigateUp,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = iconColor),
                    content = { Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "") },
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .focusable(true)
                )
            } else {
                val activity = LocalContext.current as Activity
                TextButton(onClick = { activity.onBackPressed() }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier.size(12.dp),
                            tint = iconColor
                        )
                        Text(
                            text = "back to app",
                            style = MaterialTheme.typography.labelSmall,
                            color = iconColor
                        )
                    }
                }
            }
        },
        actions = {
            IconButton(onClick = { displayMenu = !displayMenu }) {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_more_vert_24),
                    contentDescription = "More",
                    tint = iconColor,
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(moreRotation)
                )
            }

            DropdownMenu(
                expanded = displayMenu,
                onDismissRequest = { displayMenu = false },
            ) {
                AppTopBarMenu(item = item)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}

@Preview("AppTopBar")
@Composable
private fun AppTopBarPreview() {
    AppTopBar(
        item = ItemBuilder.preview(),
        canBack = true,
        navigateUp = { }
    )
}