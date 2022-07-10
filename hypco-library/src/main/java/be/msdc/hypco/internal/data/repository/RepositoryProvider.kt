package be.msdc.hypco.internal.data.repository

import android.content.Context
import be.msdc.hypco.internal.data.room.HypCoDatabase

internal object RepositoryProvider {

    private var itemsRepository: INodeItemRepository? = null

    fun items(): INodeItemRepository {
        return checkNotNull(itemsRepository) { "items repository not initialized" }
    }

    fun initialize(appContext: Context) {
        if (itemsRepository == null) {
            val db = HypCoDatabase.create(appContext)
            itemsRepository = NodeItemRoomRepository(db)
        }
    }

    fun close() {
        itemsRepository = null
    }
}