package be.msdc.hypco.internal.data.room.converter

import androidx.room.TypeConverter
import be.msdc.hypco.api.model.ItemImage

internal class ItemImageConverter {
    @TypeConverter
    fun fromType(type: ItemImage?): String? {
        return type?.raw
    }

    @TypeConverter
    fun toType(raw: String?): ItemImage? {
        return raw?.let { ItemImage.values().firstOrNull { it.raw == raw } ?: ItemImage.DEFAULT_IMG }
    }
}