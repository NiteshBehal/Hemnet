package com.hemnet.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hemnet.assignment.data.db.PropertyDb
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.data.network.PropertyListApiMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
    private val apiMock: PropertyListApiMock,
    private val db: PropertyDb
) {

    val getPropertyListData = liveData {
        emitSource(db.propertyDao().getAllProperties())
        withContext(Dispatchers.IO) {
            if (db.propertyDao().getPropertiesCount() == 0) {
                apiMock.getPropertyList()?.items?.let {
                    db.propertyDao().insertAll(it)
                }
            }
        }
    }

    fun getPropertyDetails(id: String): LiveData<Property> {
        return db.propertyDao().getPropertyDetails(id)
    }

    suspend fun toggleFavourite(selectedPropertyId: String, fav: Boolean) {
        db.propertyDao().toggleFavourite(selectedPropertyId, fav)
    }

    fun getFavouriteProperties(): LiveData<List<Property>> {
        return db.propertyDao().getFavouriteProperties()
    }
}