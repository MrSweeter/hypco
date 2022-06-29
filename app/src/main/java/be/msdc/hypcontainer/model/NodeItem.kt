package be.msdc.hypcontainer.model

import java.util.*

class NodeItem(
    var id: String,
    var title: String,
    var subtitle: String?,
    var description: String?,
    var image: Int? = null,
    var date: Date,
    var type: ItemType = ItemType.DEFAULT,
) {
    var children: List<NodeItem> = emptyList()
        private set
    var parent: NodeItem? = null
        private set

    init {
        children.forEach { it.parent = this }
    }

    val isParent: Boolean
        get() = children.isNotEmpty()
    val isLeaf: Boolean
        get() = children.isEmpty()

    fun updateChildren(newChildren: List<NodeItem>) {
        children = newChildren
        children.forEach { it.parent = this }
    }

    fun find(findID: String): NodeItem? {
        if (id == findID) {
            return this
        }
        if (isParent)   {
            return this.children.map { it.find(findID) }.firstOrNull { it != null }
        }
        return null
    }
}