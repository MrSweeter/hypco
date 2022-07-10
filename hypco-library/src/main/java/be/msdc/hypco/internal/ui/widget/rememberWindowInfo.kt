package be.msdc.hypco.internal.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes

@Composable
internal fun rememberWindowInfo(): AppWindowInfo {
    val config = LocalConfiguration.current
    return AppWindowInfo(
        screenWidthInfo = when {
            config.screenWidthDp < 600 -> AppWindowInfo.WindowType.Compact
            config.screenWidthDp < 840 -> AppWindowInfo.WindowType.Medium
            else -> AppWindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when {
            config.screenWidthDp < 480 -> AppWindowInfo.WindowType.Compact
            config.screenWidthDp < 900 -> AppWindowInfo.WindowType.Medium
            else -> AppWindowInfo.WindowType.Expanded
        },
        screenWidth = config.screenWidthDp.dp,
        screenHeight = config.screenHeightDp.dp,
    )

}

internal data class AppWindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp,
) {
    sealed class WindowType {
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}