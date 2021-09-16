package com.hemnet.assignment.di.application

import android.content.Context
import com.hemnet.assignment.data.db.PropertyDb
import com.hemnet.assignment.data.network.PropertyListApiMock
import dagger.BindsInstance
import dagger.Component

/**
 * ApplicationComponent class to inject Application level dependencies
 */
@ApplicationScope
@Component(
    modules = [
        ApiModule::class,
        DBModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun getPropertyListApiMock(): PropertyListApiMock

    fun getPropertyDb(): PropertyDb

}