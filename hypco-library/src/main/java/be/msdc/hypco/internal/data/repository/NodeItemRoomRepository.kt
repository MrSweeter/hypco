package be.msdc.hypco.internal.data.repository

import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.data.room.HypCoDatabase

internal class NodeItemRoomRepository(private val database: HypCoDatabase) : INodeItemRepository {

    private val itemsDao
        get() = database.nodeItemDao()

    override suspend fun insertItem(vararg items: NodeItem) {
        return itemsDao.insert(*items)
    }

    override suspend fun updateItem(item: NodeItem) {
        return itemsDao.update(item)
    }

    override suspend fun deleteOld(threshold: Long) {
        return itemsDao.deleteOld(threshold)
    }

    override suspend fun deleteAll() {
        return itemsDao.deleteAll()
    }

    override suspend fun getAll(): List<NodeItem> {
        return itemsDao.getAll()
    }

    override suspend fun getByID(id: String): NodeItem? {
        return itemsDao.getByID(id).firstOrNull()
    }
}