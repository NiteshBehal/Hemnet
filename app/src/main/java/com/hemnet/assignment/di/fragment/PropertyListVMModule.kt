package com.hemnet.assignment.di.fragment

import androidx.lifecycle.ViewModel
import com.hemnet.assignment.ui.viewmodels.PropertyListFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PropertyListVMModule {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(PropertyListFragmentVM::class)
    abstract fun bindsPropertyListVM(
        viewModel: PropertyListFragmentVM
    ): ViewModel
}