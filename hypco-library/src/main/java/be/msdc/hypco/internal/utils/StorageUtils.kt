package be.msdc.hypco.internal.utils

import android.content.ContentResolver
import android.net.Uri
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.NodeItem
import com.google.gson.GsonBuilder
import java.io.*

internal object StorageUtils {

    private val gson = GsonBuilder().setPrettyPrinting().create()
    const val type = "text/json"

    fun read(contentResolver: ContentResolver, uri: Uri): List<NodeItem> {
        return try {
            contentResolver.openInputStream(uri).use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    val json = reader.readText()
                    val item = gson.fromJson(json, NodeItem::class.java)
                    return updateAfterImport(item)
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            emptyList()
        }
    }

    fun write(contentResolver: ContentResolver, uri: Uri, nodeItem: NodeItem) {
        try {
            contentResolver.openOutputStream(uri).use { outputStream ->
                BufferedWriter(OutputStreamWriter(outputStream)).use { writer ->
                    val json = gson.toJson(nodeItem)
                    writer.write(json)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun updateAfterImport(item: NodeItem): List<NodeItem> {
        val items = updateItemAfterImport(item).filter {
            it.id != ItemBuilder.ROOT_ID
        }
        return items
    }

    private fun updateItemAfterImport(item: NodeItem): MutableList<NodeItem> {
        val items = mutableListOf<NodeItem>()
        item.children.forEach {
            it.parentID = item.id
            items.addAll(updateItemAfterImport(it))
        }
        items.add(item)
        return items
    }
}