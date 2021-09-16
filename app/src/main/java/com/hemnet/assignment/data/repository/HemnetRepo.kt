package com.hemnet.assignment.data.repository

import com.hemnet.assignment.data.db.PropertyDb
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.data.network.PropertyListApiMock
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repo class to fetch data.
 * This layer is manage the data endpoints and has access of both network and DB layers.
 *
 * In this assignment im am taking Room DB as single source of truth.
 * So, first time when DB is empty, I are fetching the data from data.json file and inserting it in DB
 * and then every screen is getting the data from DB only.
 *
 *
 */
class HemnetRepo @Inject constructor(
    val apiMock: PropertyListApiMock,
    val db: PropertyDb
) {
    suspend fun getPropertyListData(): Flow<List<Property>> {
        if (db.propertyDao().getPropertiesCount() == 0) {
            apiMock.getPropertyList()?.items?.let {
                db.propertyDao().insertAll(it)
            }
        }
        return db.propertyDao().getAllProperties()
    }

    fun getPropertyDetails(id: String): Flow<Property> {
        return db.propertyDao().getPropertyDetails(id)
    }

    suspend fun toggleFavourite(selectedPropertyId: String, fav: Boolean) {
        db.propertyDao().toggleFavourite(selectedPropertyId, fav)
    }

    fun getFavouriteProperties(): Flow<List<Property>> {
        return db.propertyDao().getFavouriteProperties()
    }
}