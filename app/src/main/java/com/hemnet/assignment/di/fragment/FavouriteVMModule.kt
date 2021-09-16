package com.hemnet.assignment.di.fragment

import androidx.lifecycle.ViewModel
import com.hemnet.assignment.ui.viewmodels.FavouriteFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavouriteVMModule {
    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(FavouriteFragmentVM::class)
    abstract fun bindsFavouriteVM(
        viewModel: FavouriteFragmentVM
    ): ViewModel
}