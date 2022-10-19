package be.msdc.hypco.api.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "items")
class NodeItem(
    @PrimaryKey var id: String,
    var state: ItemState = ItemState.UNKNOWN,
    var title: String,
    var subtitle: String?,
    var description: String?,
    var itemImage: ItemImage? = null,
    var date: Date,
    var type: ItemType = ItemType.DEFAULT,
    var content: String? = null,
    var contentType: ItemContentType = ItemContentType.NOTHING,
    var progress: Float = 0f,
    var maxProgress: Float = 0f,
    var progressText: String? = null,
    var link: String? = null
) {
    //#region Public
    var parentID: String? = null
        get() = field ?: parent?.parentID
    fun updateChildren(newChildren: List<NodeItem>) {
        children = newChildren.toMutableSet()
        updateChildDependencies()
    }

    fun addChildren(child: NodeItem) {
        children.add(child)
        updateChildDependencies()
    }
    //#endregion

    //#region Internal
    @Transient
    @Ignore
    internal var parent: NodeItem? = null
        private set(value) {
            field = value
            parentID = value?.id
        }

    @Ignore
    internal var children: MutableSet<NodeItem> = mutableSetOf()
        private set

    @Transient
    @Ignore
    internal var collectionTypes: Set<ItemType.CollectionType> = emptySet()
        private set

    init {
        updateChildDependencies()
    }

    internal val isClickable: Boolean
        get() = contentType != ItemContentType.NOTHING || children.isNotEmpty() || type == ItemType.SIMPLE_LINK
    internal val isLongClickable: Boolean
        get() = contentType != ItemContentType.NOTHING && children.isNotEmpty()
    internal val isListParent: Boolean
        get() = collectionTypes.firstOrNull() == ItemType.CollectionType.LIST
    internal val isGridParent: Boolean
        get() = collectionTypes.firstOrNull() == ItemType.CollectionType.GRID
    internal val isSplit: Boolean
        get() = collectionTypes.size > 1

    internal fun find(findID: String): NodeItem? {
        if (id == findID) {
            return this
        }
        if (children.isNotEmpty()) {
            return this.children.map { it.find(findID) }.firstOrNull { it != null }
        }
        return null
    }

    internal fun getChildren(type: ItemType.CollectionType): Set<NodeItem> {
        return children.filter { it.type.collectionType == type }.toSet()
    }

    internal fun getFormattedContent(): String {
        return contentType.format(content) ?: content ?: ""
    }

    internal fun getNotificationText(): String  {
        return "[$state] $title"
    }

    private fun updateChildDependencies() {
        children.forEach { it.parent = this }
        collectionTypes = children.map { it.type.collectionType }.toSet()
    }

    //#endregion

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return (other as? NodeItem)?.id == id
    }
}