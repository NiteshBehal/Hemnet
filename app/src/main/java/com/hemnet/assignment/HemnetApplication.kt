package com.hemnet.assignment

import android.app.Application
import com.hemnet.assignment.di.ComponentFactory
import com.hemnet.assignment.di.application.ApplicationComponent

class HemnetApplication : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = ComponentFactory.createApplicationComponent(this)
    }

}