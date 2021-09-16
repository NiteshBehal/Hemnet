package com.hemnet.assignment.di.fragment

import androidx.lifecycle.ViewModel
import com.hemnet.assignment.ui.viewmodels.PropertyDetailsFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PropertyDetailsVMModule {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(PropertyDetailsFragmentVM::class)
    abstract fun bindsPropertyDetailVM(
        viewModel: PropertyDetailsFragmentVM
    ): ViewModel
}