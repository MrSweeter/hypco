package be.msdc.hypco.internal.data.repository

import be.msdc.hypco.api.model.NodeItem

internal interface INodeItemRepository {

    suspend fun insertItem(vararg items: NodeItem)
    suspend fun updateItem(item: NodeItem)

    suspend fun deleteOld(threshold: Long)
    suspend fun deleteAll()

    suspend fun getAll(): List<NodeItem>
    suspend fun getByID(id: String): NodeItem?

}
