package com.hemnet.assignment.di.fragment

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class VMFectoryModule {
    @FragmentScope
    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

}