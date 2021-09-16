package com.hemnet.assignment.di.fragment

import com.hemnet.assignment.di.application.ApplicationComponent
import com.hemnet.assignment.ui.fragments.FavouriteFragment
import com.hemnet.assignment.ui.fragments.PropertyDetailsFragment
import com.hemnet.assignment.ui.fragments.PropertyListFragment
import dagger.Component

/**
 * FragmentComponent class to inject Fragment level dependencies
 */

@FragmentScope
@Component(
    modules = [VMFectoryModule::class,
        PropertyListVMModule::class,
        PropertyDetailsVMModule::class,
        FavouriteVMModule::class],
    dependencies = [ApplicationComponent::class]
)
interface FragmentComponent {
    fun inject(fragment: PropertyListFragment)
    fun inject(fragment: PropertyDetailsFragment)
    fun inject(fragment: FavouriteFragment)
}