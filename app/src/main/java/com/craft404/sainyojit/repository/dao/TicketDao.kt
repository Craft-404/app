package com.craft404.sainyojit.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.craft404.sainyojit.repository.entity.TicketEntity

@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TicketEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<TicketEntity>)

    @Query("SELECT * FROM ticket")
    fun getAll(): LiveData<List<TicketEntity>>

    @Query("SELECT * FROM ticket WHERE id = :id")
    fun getById(id: Long): LiveData<TicketEntity>

    @Delete
    suspend fun delete(entity: TicketEntity)

    @Query("DELETE FROM ticket")
    suspend fun deleteAll()
}