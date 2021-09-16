package com.hemnet.assignment.di.application

import android.content.Context
import com.hemnet.assignment.data.network.PropertyListApiMock
import dagger.Module
import dagger.Provides

/**
 * In Actual scenario we will inject Retrofit or any api related dependency from this class
 * Currently injecting the Mock dependency
 * */
@Module
class ApiModule {
    @Provides
    @ApplicationScope
    fun providePropertyListApiMock(context: Context): PropertyListApiMock {
        return PropertyListApiMock(context)
    }
}