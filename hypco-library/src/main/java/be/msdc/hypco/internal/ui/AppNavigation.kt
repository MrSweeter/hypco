package be.msdc.hypco.internal.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import be.msdc.hypco.internal.navigation.AppDestination.ITEM_ID_KEY
import be.msdc.hypco.internal.navigation.AppDestination.ITEM_ROUTE
import be.msdc.hypco.internal.navigation.NavigationAction
import be.msdc.hypco.internal.ui.activity.MainViewModel
import be.msdc.hypco.internal.ui.screen.DetailScreen
import be.msdc.hypco.internal.ui.screen.ItemScreen

@Composable
internal fun AppNavigation(viewModel: MainViewModel = viewModel()) {

    val navController = rememberNavController()
    val actions = remember(navController) { NavigationAction(navController) }

    viewModel.load(LocalContext.current)

    NavHost(
        navController,
        ITEM_ROUTE,
    ) {
        composable(ITEM_ROUTE) {
            viewModel.rootNode?.let {
                ItemScreen(it, navController, actions.selectItem, actions.navigateUp)
            } ?: Text(text = "Item not found")
        }
        composable(
            "$ITEM_ROUTE/{${ITEM_ID_KEY}}",
            arguments = listOf(navArgument(ITEM_ID_KEY) { type = NavType.StringType }),
        ) { entry ->
            val arguments = requireNotNull(entry.arguments)
            viewModel.findNode(arguments[ITEM_ID_KEY] as String)?.let {
                ItemScreen(it, navController, actions.selectItem, actions.navigateUp)
            } ?: Text(text = "Item not found")
        }
        composable(
            "$ITEM_ROUTE/{${ITEM_ID_KEY}}/details",
            arguments = listOf(navArgument(ITEM_ID_KEY) { type = NavType.StringType }),
        ) { entry ->
            val arguments = requireNotNull(entry.arguments)
            viewModel.findNode(arguments[ITEM_ID_KEY] as String)?.let {
                DetailScreen(it, navController, actions.navigateUp, actions.selectItem)
            } ?: Text(text = "Item not found")
        }
    }
}