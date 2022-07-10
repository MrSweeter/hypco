package be.msdc.hypco.internal.data.room.converter

import androidx.room.TypeConverter
import be.msdc.hypco.api.model.ItemType

internal class ItemTypeConverter {
    @TypeConverter
    fun fromType(type: ItemType): String {
        return type.name
    }

    @TypeConverter
    fun toType(name: String): ItemType {
        return ItemType.valueOf(name)
    }
}