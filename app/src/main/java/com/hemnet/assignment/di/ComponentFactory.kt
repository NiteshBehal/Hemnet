package com.hemnet.assignment.di

import android.content.Context
import com.hemnet.assignment.HemnetApplication
import com.hemnet.assignment.di.application.ApplicationComponent
import com.hemnet.assignment.di.application.DaggerApplicationComponent
import com.hemnet.assignment.di.fragment.DaggerFragmentComponent
import com.hemnet.assignment.di.fragment.FragmentComponent

/**
 * Factory class to provide Dagger components
 */
object ComponentFactory {

    fun createApplicationComponent(context: Context): ApplicationComponent {
        return DaggerApplicationComponent.builder().application(context).build()
    }

    fun createFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
            .applicationComponent(HemnetApplication.applicationComponent)
            .build()
    }
}