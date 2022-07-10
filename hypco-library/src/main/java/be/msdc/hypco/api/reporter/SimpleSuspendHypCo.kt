package be.msdc.hypco.api.reporter

import be.msdc.hypco.api.HypCo
import be.msdc.hypco.api.model.NodeItem

open class SimpleSuspendHypCo internal constructor() : IHypCoReporter() {

    /**
     * Suspend function to access HypCo Storage
     * Report a new node item to the HypCo Storage.
     * The new node item will be considered as Layer 1
     * @param item The node item to report.
     */
    suspend fun reportLayer1Suspend(item: NodeItem) {
        reportLayersSuspend(
            item = item
        )
    }

    /**
     * Suspend function to access HypCo Storage
     * Report two node items to the HypCo Storage.
     * The first node item will be considered as Layer 3
     * The second node item will be considered as Layer 2 of the first node item.
     * @param item The node item to report.
     * @param parent The parent node item to report.
     */
    suspend fun reportLayer2Suspend(item: NodeItem, parent: NodeItem) {
        reportLayersSuspend(
            item = item,
            parent = parent
        )
    }

    /**
     * Suspend function to access HypCo Storage
     * Report two node items to the HypCo Storage.
     * The first node item will be considered as Layer 2 of the parent node with the given ID
     * @param item The node item to report.
     * @param parentID The parent ID of the node item to link.
     */
    suspend fun reportLayer2Suspend(item: NodeItem, parentID: String) {
        val safeParent =
            repository.getByID(parentID) ?: throw IllegalArgumentException("Parent with ID $parentID not found")
        reportLayersSuspend(
            item = item,
            parent = safeParent.apply { this.parentID = null }
        )
    }

    /**
     * Suspend function to access HypCo Storage
     * Report three node items to the HypCo Storage.
     * The first node item will be considered as Layer 3
     * The second node item will be considered as Layer 2 of the first node item.
     * The third node item will be considered as Layer 1 of the second node item.
     * @param item The node item to report.
     * @param parent The parent node item to report.
     * @param grandParent The grand-parent of the node item to report.
     */
    suspend fun reportLayer3Suspend(item: NodeItem, parent: NodeItem, grandParent: NodeItem) {
        reportLayersSuspend(
            item = item,
            parent = parent,
            grandParent = grandParent
        )
    }

    /**
     * Suspend function to access HypCo Storage
     * Report three node items to the HypCo Storage.
     * The first node item will be considered as Layer 3 of the parent node with the first given ID.
     * The second node item will be considered as Layer 2 of the first node with the second given ID.
     * @param item The node item to report.
     * @param parentID The parent ID of the node item to link.
     * @param grandParentID The grand-parent ID of the node item to link.
     */
    suspend fun reportLayer3Suspend(item: NodeItem, parentID: String, grandParentID: String) {
        val safeParent =
            repository.getByID(parentID) ?: throw IllegalArgumentException("Parent with ID $parentID not found")
        val safeGrandParent = repository.getByID(grandParentID)
            ?: throw IllegalArgumentException("GrandParent with ID $grandParentID not found")
        reportLayersSuspend(
            item = item,
            parent = safeParent,
            grandParent = safeGrandParent
        )
    }

    /**
     * Suspend function to access HypCo Storage
     * Report node item(s) to the HypCo Storage.
     * 3 Generations of node items are supported.
     * @param item The node item to report.
     * @param parent The parent node item to report.
     * @param grandParent The grand-parent of the node item to report.
     */
    suspend fun reportLayersSuspend(
        item: NodeItem,
        parent: NodeItem? = null,
        grandParent: NodeItem? = null,
    ) {
        item.parentID = parent?.id
        parent?.parentID = grandParent?.id
        grandParent?.parentID = null

        val toInsert = listOfNotNull(item, parent, grandParent)
        reportSuspend(*toInsert.toTypedArray())
    }

    /**
     * Suspend function to access HypCo Storage
     * Report a new or multiple node item(s) to the HypCo Storage.
     * No generations dependencies impact will occur.
     * @param items The node item(s) to report.
     */
    suspend fun reportSuspend(vararg items: NodeItem) {
        if (HypCo.showNotification) HypCo.notificationUtils?.show(*items)
        repository.insertItem(*items)
    }
}