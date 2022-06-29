package be.msdc.hypcontainer.model

import androidx.annotation.DrawableRes
import be.msdc.hypcontainer.R
import java.util.*

enum class ItemType {
    CARD,
    DEFAULT
}

enum class ImageType(@DrawableRes val drawable: Int) {
    TV(R.drawable.ic_baseline_tv_24),
    PHONE_ANDROID(R.drawable.ic_baseline_phone_android_24),
    PHONE_IOS(R.drawable.ic_baseline_phone_iphone_24),
    TABLET_ANDROID(R.drawable.ic_baseline_tablet_android_24),
    TABLET_IOS(R.drawable.ic_baseline_tablet_mac_24),
}

class ItemBuilder {

    private var id: String = UUID.randomUUID().toString()
    private var date: Date = Date()

    private var subtitle: String? = null
    private var description: String? = null
    @DrawableRes
    private var image: Int? = null

    private var children: List<NodeItem>? = null

    fun build(title: String, type: ItemType): NodeItem   {
        val childList = children
        return NodeItem(
            id = id,
            title = title,
            subtitle = subtitle,
            description = description,
            image = image,
            date = date
        )
            .apply {
                updateChildren(childList ?: emptyList())
            }
    }

    companion object {
        fun preview(): NodeItem  {
            val id = UUID.randomUUID().toString().substring(0, 5)
            return NodeItem(id,
                "name-$id",
                null,
                "",
                null,
                Date(),
                ItemType.DEFAULT
            )
        }
        fun tree(): NodeItem    {
            return NodeItem("ROOT",
                "ROOT",
                null,
                "",
                null,
                Date(),
                ItemType.DEFAULT,
            ).apply {
                updateChildren(listOf(
                    preview().apply { type = ItemType.CARD },
                    preview().apply { type = ItemType.CARD },
                    preview().apply { type = ItemType.CARD },
                    preview().apply { type = ItemType.CARD },
                    preview().apply { type = ItemType.CARD },
                    preview().apply {
                        type = ItemType.CARD
                        updateChildren(listOf(
                            preview().apply { type = ItemType.CARD },
                            preview().apply { type = ItemType.CARD },
                            preview().apply { type = ItemType.CARD },
                            preview().apply { type = ItemType.CARD },
                            preview().apply { type = ItemType.CARD },
                            preview().apply {
                                type = ItemType.CARD
                                updateChildren(listOf(
                                    preview(),
                                    preview(),
                                ))
                            }
                        ))
                    },
                ))
            }
        }
    }
}