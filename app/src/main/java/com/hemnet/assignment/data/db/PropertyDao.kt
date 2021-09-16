package com.hemnet.assignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hemnet.assignment.data.models.Property
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Room DB.
 * This file includes the methods to insert fetch or save the data to DB
 */
@Dao
interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Property>)

    @Query("SELECT * FROM property order by _id")
    fun getAllProperties(): Flow<List<Property>>

    @Query("SELECT * FROM property where id = :id")
    fun getPropertyDetails(id: String): Flow<Property>

    @Query("SELECT count(*) FROM property")
    suspend fun getPropertiesCount(): Int

    @Query("UPDATE property SET isFav=:isFav WHERE id = :id")
    suspend fun toggleFavourite(id: String, isFav: Boolean): Int

    @Query("SELECT * FROM property where isFav = 1")
    fun getFavouriteProperties(): Flow<List<Property>>
}