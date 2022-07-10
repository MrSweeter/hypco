package be.msdc.hypco.api

import android.content.Context
import be.msdc.hypco.R
import be.msdc.hypco.api.model.*
import java.util.*

class ItemBuilder internal constructor() {

    private var id: String = UUID.randomUUID().toString()
    private var state: ItemState = ItemState.UNKNOWN
    private var date: Date = Date()

    private var type: ItemType = ItemType.DEFAULT

    private var subtitle: String? = null
    private var description: String? = null
    private var itemImage: ItemImage? = null
    private var content: String? = null
    private var contentType: ItemContentType = ItemContentType.NOTHING

    private var progress: Float = 0f
    private var maxProgress: Float = 0f
    private var progressText: String? = null

    private var children: List<NodeItem>? = null

    fun withId(id: String): ItemBuilder {
        if (id == ROOT_ID) throw IllegalArgumentException("Id cannot be the root id")
        this.id = id
        return this
    }

    fun withState(state: ItemState): ItemBuilder {
        this.state = state
        return this
    }

    fun withDate(date: Date): ItemBuilder {
        this.date = date
        return this
    }

    fun withType(type: ItemType): ItemBuilder {
        this.type = type
        return this
    }

    fun withProgress(progress: Float, maxProgress: Float, progressText: String? = null): ItemBuilder {
        this.progress = progress
        this.maxProgress = maxProgress
        this.progressText = progressText
        return this
    }

    fun withSubtitle(subtitle: String): ItemBuilder {
        this.subtitle = subtitle
        return this
    }

    fun withDescription(description: String): ItemBuilder {
        this.description = description
        return this
    }

    fun withImage(imageType: ItemImage): ItemBuilder {
        this.itemImage = imageType
        return this
    }

    fun withContent(content: String, type: ItemContentType): ItemBuilder {
        this.content = content
        this.contentType = type
        return this
    }

    fun withChildren(children: List<NodeItem>): ItemBuilder {
        this.children = children
        return this
    }

    fun build(title: String): NodeItem {
        val childList = children ?: emptyList()
        return NodeItem(
            id = id,
            state = state,
            title = title,
            subtitle = subtitle,
            description = description,
            itemImage = itemImage,
            date = date,
            type = type,
            content = content,
            contentType = contentType,
            progress = progress,
            maxProgress = maxProgress,
            progressText = progressText,
        ).apply {
            updateChildren(childList)
        }
    }

    companion object {

        val instance
            get() = ItemBuilder()
        internal const val ROOT_ID = "hypco_root_internal_id"

        internal fun getRoot(context: Context, children: List<NodeItem>): NodeItem {
            return ItemBuilder()
                .apply { id = ROOT_ID }
                .withChildren(children)
                .build(context.getString(R.string.library_name))
        }

        fun preview(): NodeItem {
            val id = UUID.randomUUID().toString().substring(0, 5)
            return instance
                .withId(id)
                .withSubtitle("This is a preview subtitle")
                .withDescription("This is a preview description")
                .withContent("This is a preview content", ItemContentType.TEXT)
                .build("Title-$id")
        }

        fun jsonPreview(): NodeItem {
            val id = UUID.randomUUID().toString().substring(0, 5)
            val content = "[{\"_id\":\"62cb1103d7ded1a85852548a\",\"index\":0,\"guid\":\"016e550b-b0b2-4687-9f50-073a2fcc3203\",\"isActive\":true,\"balance\":\"\$3,598.41\",\"picture\":\"http://placehold.it/32x32\",\"age\":22,\"eyeColor\":\"blue\",\"name\":\"Lola Guzman\",\"gender\":\"female\",\"company\":\"BIOLIVE\",\"email\":\"lolaguzman@biolive.com\",\"phone\":\"+1 (996) 512-2010\",\"address\":\"178 Maple Street, Delco, Utah, 2955\",\"about\":\"Pariatur consequat et qui et excepteur deserunt mollit fugiat aliquip ex nulla aute sunt nisi. Esse nisi ipsum aute proident anim labore ullamco ex Lorem in elit proident laborum aliquip. Ex sint adipisicing est nisi tempor dolore sint sit sunt est tempor velit veniam. Deserunt veniam exercitation anim ex est anim sunt. Sunt enim do elit adipisicing sunt irure ad laborum.\\r\\n\",\"registered\":\"2020-07-24T10:57:46 -02:00\",\"latitude\":24.252813,\"longitude\":-94.883323,\"tags\":[\"duis\",\"incididunt\",\"in\",\"aliqua\",\"deserunt\",\"eu\",\"exercitation\"],\"friends\":[{\"id\":0,\"name\":\"Bianca Serrano\"},{\"id\":1,\"name\":\"Peggy Graves\"},{\"id\":2,\"name\":\"Ivy Hurst\"}],\"greeting\":\"Hello, Lola Guzman! You have 10 unread messages.\",\"favoriteFruit\":\"apple\"}]"
            return instance
                .withId(id)
                .withSubtitle("This is a preview subtitle")
                .withDescription("This is a preview description")
                .withContent(content, ItemContentType.JSON)
                .build("Title-$id")
        }

        fun tree(): NodeItem {
            return instance
                .withId(ROOT_ID)
                .build("Root")
                .apply {
                    updateChildren(
                        listOf(
                            preview().apply { type = ItemType.SIMPLE_TEXT },
                            preview().apply { type = ItemType.SQUARE_CARD },
                            preview().apply { type = ItemType.PROGRESS_CARD },
                            preview().apply { type = ItemType.SIMPLE_CARD },
                            preview().apply { type = ItemType.SIMPLE_CARD },
                            preview().apply {
                                type = ItemType.SIMPLE_CARD
                                updateChildren(listOf(
                                    preview().apply { type = ItemType.SIMPLE_CARD },
                                    preview().apply { type = ItemType.SIMPLE_CARD },
                                    preview().apply { type = ItemType.SIMPLE_CARD },
                                    preview().apply { type = ItemType.SIMPLE_CARD },
                                    preview().apply { type = ItemType.SIMPLE_CARD },
                                    preview().apply {
                                        type = ItemType.SIMPLE_CARD
                                        updateChildren(
                                            listOf(
                                                preview(),
                                                preview(),
                                            )
                                        )
                                    }
                                ))
                            },
                        )
                    )
                }
        }
    }
}