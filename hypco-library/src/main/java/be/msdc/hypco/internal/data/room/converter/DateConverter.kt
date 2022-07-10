package be.msdc.hypco.internal.data.room.converter

import androidx.room.TypeConverter
import java.util.*

internal class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}