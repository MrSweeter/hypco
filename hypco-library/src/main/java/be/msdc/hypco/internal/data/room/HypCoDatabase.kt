package be.msdc.hypco.internal.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.data.room.converter.DateConverter
import be.msdc.hypco.internal.data.room.converter.ItemImageConverter
import be.msdc.hypco.internal.data.room.converter.ItemTypeConverter

@Database(entities = [NodeItem::class], version = 1, exportSchema = false)
@TypeConverters(ItemTypeConverter::class, ItemImageConverter::class, DateConverter::class)
internal abstract class HypCoDatabase : RoomDatabase() {

    abstract fun nodeItemDao(): NodeItemDao

    companion object {
        private const val DATABASE_NAME = "hypco.db"

        fun create(appContext: Context): HypCoDatabase {
            return Room.databaseBuilder(appContext, HypCoDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}