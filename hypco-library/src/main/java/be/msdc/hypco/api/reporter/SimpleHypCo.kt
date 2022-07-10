package be.msdc.hypco.api.reporter

import be.msdc.hypco.api.model.NodeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SimpleHypCo internal constructor() : SimpleSuspendHypCo(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    /**
     * Report a new or multiple node item(s) to the HypCo Storage.
     * @param items The node item(s) to report.
     */
    fun report(vararg items: NodeItem) = launch(errorHandler) { reportSuspend(*items) }

    /**
     * Report a new node item to the HypCo Storage.
     * The new node item will be considered as Layer 1
     * @param item The node item to report.
     */
    fun reportLayer1(item: NodeItem) = launch(errorHandler) { reportLayer1Suspend(item) }

    /**
     * Report two node items to the HypCo Storage.
     * The first node item will be considered as Layer 3
     * The second node item will be considered as Layer 2 of the first node item.
     * @param item The node item to report.
     * @param parent The parent node item to report.
     */
    fun reportLayer2(item: NodeItem, parent: NodeItem) = launch(errorHandler) { reportLayer2Suspend(item, parent) }

    /**
     * Report two node items to the HypCo Storage.
     * The first node item will be considered as Layer 2 of the parent node with the given ID
     * @param item The node item to report.
     * @param parentID The parent ID of the node item to link.
     */
    fun reportLayer2(item: NodeItem, parentID: String) = launch(errorHandler) { reportLayer2Suspend(item, parentID) }

    /**
     * Report three node items to the HypCo Storage.
     * The first node item will be considered as Layer 3
     * The second node item will be considered as Layer 2 of the first node item.
     * The third node item will be considered as Layer 1 of the second node item.
     * @param item The node item to report.
     * @param parent The parent node item to report.
     * @param grandParent The grand-parent of the node item to report.
     */
    fun reportLayer3(item: NodeItem, parent: NodeItem, grandParent: NodeItem) =
        launch(errorHandler) { reportLayer3Suspend(item, parent, grandParent) }

    /**
     * Report three node items to the HypCo Storage.
     * The first node item will be considered as Layer 3 of the parent node with the first given ID.
     * The second node item will be considered as Layer 2 of the first node with the second given ID.
     * @param item The node item to report.
     * @param parentID The parent ID of the node item to link.
     * @param grandParentID The grand-parent ID of the node item to link.
     */
    fun reportLayer3(item: NodeItem, parentID: String, grandParentID: String) =
        launch(errorHandler) { reportLayer3Suspend(item, parentID, grandParentID) }
}