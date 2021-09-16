package com.hemnet.assignment.data.network

import android.content.Context
import com.google.gson.Gson
import com.hemnet.assignment.data.models.PropertyListModel
import java.io.IOException
import javax.inject.Inject

class PropertyListApiMock @Inject constructor(val context: Context) {

    /**
     * In Actual Scenario this class will be replaced with retrofit api code to fetch data from server.
     * As of now, I am reading the dummy data from data.json file from assets folder.
     */
    fun getPropertyList(): PropertyListModel? {
        val itemList: PropertyListModel
        try {
            val jsonString =
                context.assets.open("data.json").bufferedReader()
                    .use { it.readText() }
            val gson = Gson()
            itemList = gson.fromJson(jsonString, PropertyListModel::class.java)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return itemList
    }
}