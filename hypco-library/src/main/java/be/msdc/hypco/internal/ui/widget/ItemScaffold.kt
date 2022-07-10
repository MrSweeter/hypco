package be.msdc.hypco.internal.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import be.msdc.hypco.api.model.NodeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ItemScaffold(
    item: NodeItem,
    navController: NavController,
    navigateUp: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(item = item, canBack = navController.previousBackStackEntry != null, navigateUp = navigateUp)
        },
    ) { pad ->
        Column(modifier = Modifier.padding(top = pad.calculateTopPadding())) {
            content()
        }
    }
}