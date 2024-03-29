package com.gx.room.task

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseRoomDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<T?>?): LongArray?

    @Delete
    suspend fun delete(vararg data: T?)

    @Delete
    fun deleteAll(data: List<T?>?)

}