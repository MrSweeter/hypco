package be.msdc.hypco.internal.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.data.repository.RepositoryProvider
import be.msdc.hypco.internal.ui.AppNavigation
import be.msdc.hypco.internal.ui.theme.HypCoTheme
import be.msdc.hypco.internal.utils.StorageUtils
import be.msdc.hypco.internal.utils.StorageUtils.type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal class MainActivity : BaseHypCoActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RepositoryProvider.initialize(applicationContext)

        setContent {
            HypCoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }

    fun export(item: NodeItem) {
        val exportIntent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        exportIntent.addCategory(Intent.CATEGORY_OPENABLE)
        exportIntent.type = type
        val filename = "${item.id}.json"
        exportIntent.putExtra(Intent.EXTRA_TITLE, filename)
        exportItem = item
        exportForResult.launch(exportIntent)
    }

    private var exportItem: NodeItem? = null
    private val exportForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    exportItem?.let { item ->
                        StorageUtils.write(contentResolver, uri, item)
                    }
                }
            }
        }
}