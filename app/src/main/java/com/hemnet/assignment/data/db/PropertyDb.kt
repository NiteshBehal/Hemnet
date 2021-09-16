package com.hemnet.assignment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hemnet.assignment.data.models.Property

/**
 * The Actual Room DB Class
 */
@Database(
    entities = [Property::class],
    version = 1,
    exportSchema = false
)
abstract class PropertyDb : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao

    companion object {
        @Volatile
        private var instance: PropertyDb? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                PropertyDb::class.java,
                "PropertyDb"
            ).build()
    }
}