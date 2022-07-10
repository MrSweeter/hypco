package be.msdc.hypco.internal.data.room

import androidx.room.*
import be.msdc.hypco.api.model.NodeItem

@Dao
internal interface NodeItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg nodeItems: NodeItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(nodeItem: NodeItem)

    @Query("SELECT * FROM items ORDER BY date DESC")
    fun getAll(): List<NodeItem>

    @Query("SELECT * FROM items WHERE id=:id ORDER BY date DESC LIMIT 1")
    fun getByID(id: String): List<NodeItem>

    @Query("DELETE FROM items WHERE date <= :threshold")
    fun deleteOld(threshold: Long)

    @Query("DELETE FROM items")
    fun deleteAll()

}