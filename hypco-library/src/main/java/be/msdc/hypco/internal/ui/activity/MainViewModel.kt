package be.msdc.hypco.internal.ui.activity

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.ItemBuilder.Companion.ROOT_ID
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.data.repository.RepositoryProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class MainViewModel : ViewModel() {

    var rootNode by mutableStateOf<NodeItem?>(null)

    fun findNode(id: String): NodeItem? {
        return rootNode?.find(id)
    }

    fun load(context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val nodes = RepositoryProvider.items().getAll()
                updateRoot(context, nodes)
            }
        }
    }

    fun clearStorage(context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                RepositoryProvider.items().deleteAll()
                updateRoot(context, emptyList())
            }
        }
    }

    private fun updateRoot(context: Context, nodes: List<NodeItem>) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                rootNode = ItemBuilder.getRoot(context, rebuildTree(nodes))
            }
        }
    }

    private fun rebuildTree(nodes: List<NodeItem>): List<NodeItem> {
        val mappedParents = nodes.groupBy { it.parentID }
        nodes.forEach { node ->
            mappedParents[node.id]?.let { node.updateChildren(it) }
        }
        return nodes.filter { it.parentID == null || it.parentID == ROOT_ID }
    }
}