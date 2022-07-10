package be.msdc.hypco.internal.ui.widget

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import be.msdc.hypco.R
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.ui.activity.MainActivity
import be.msdc.hypco.internal.ui.activity.MainViewModel

@Composable
internal fun AppTopBarMenu(
    viewModel: MainViewModel = viewModel(),
    item: NodeItem,
) {
    val iconColor = MaterialTheme.colorScheme.secondary
    val context = LocalContext.current
    val activity = context as MainActivity

    DropdownMenuItem(
        text = {
            Text(text = "Export")
        }, leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_baseline_output_24),
                contentDescription = "Export",
                tint = iconColor
            )
        }, onClick = {
            activity.export(item)
        })

    DropdownMenuItem(
        text = {
            Text(text = "Clear Storage")
        }, leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_baseline_clear_24),
                contentDescription = "Clear Storage",
                tint = MaterialTheme.colorScheme.error
            )
        }, onClick = {
            viewModel.clearStorage(context)
            activity.finish()
            activity.startActivity(activity.intent)
        })
}