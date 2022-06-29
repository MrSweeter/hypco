package be.msdc.hypcontainer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import be.msdc.hypcontainer.model.NodeItem
import be.msdc.hypcontainer.ui.Item

object AppDestinations {
    const val ITEM_ROUTE = "items"
    const val ITEM_ID_KEY = "itemId"
}

private class AppActions(navController: NavHostController) {
    val selectItem: (NodeItem) -> Unit = { item ->
        navController.navigate("${AppDestinations.ITEM_ROUTE}/${item.id}")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun AppNavigation(start: NodeItem) {

    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }
    NavHost(
        navController,
        AppDestinations.ITEM_ROUTE,
    ) {
        composable(AppDestinations.ITEM_ROUTE) {
            Item(start, actions.selectItem, actions.navigateUp)
        }
        composable("${AppDestinations.ITEM_ROUTE}/{${AppDestinations.ITEM_ID_KEY}}",
            arguments = listOf(
                navArgument(AppDestinations.ITEM_ID_KEY) {
                    type = NavType.StringType
                }
            ),
        ) { entry ->
            val arguments = requireNotNull(entry.arguments)
            Item(start.find(arguments[AppDestinations.ITEM_ID_KEY] as String), actions.selectItem, actions.navigateUp)
        }
    }
}