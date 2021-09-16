package com.hemnet.assignment.di.application

import android.content.Context
import com.hemnet.assignment.data.db.PropertyDb
import dagger.Module
import dagger.Provides

/**
 * class to inject Room DB dependency
 * */
@Module
class DBModule {
    @Provides
    @ApplicationScope
    fun providePropertyDb(context: Context): PropertyDb {
        return PropertyDb(context)
    }
}