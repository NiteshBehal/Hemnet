package com.hemnet.assignment.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PropertyListModel(
    val items: List<Property>? = null
)

/**
 * Entity class for Room DB.
 * This data class holds all the columns for a DB table
 * */
@Entity(tableName = "property")
data class Property(
    @PrimaryKey(autoGenerate = true)
    val _id: Long,
    val id: String? = null,
    val type: String? = null,
    val area: String? = null,
    val image: String? = null,
    val averagePrice: String? = null,
    val rating: String? = null,
    val askingPrice: String? = null,
    val daysOnHemnet: String? = null,
    val livingArea: String? = null,
    val monthlyFee: String? = null,
    val municipality: String? = null,
    val numberOfRooms: String? = null,
    val streetAddress: String? = null,
    val isFav: Boolean = false,
)