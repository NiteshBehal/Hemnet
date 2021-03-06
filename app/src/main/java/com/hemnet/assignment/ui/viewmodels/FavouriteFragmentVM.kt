package com.hemnet.assignment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemnet.assignment.data.repository.HemnetRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteFragmentVM @Inject constructor(val repo: HemnetRepo) :
    ViewModel() {
    /**
     * livedata to hold Favourite Properties list data for ui
     */
    val favouriteList = repo.getFavouriteProperties()

    /**
     * Method to toggle Favourite status for properties
     */
    fun toggleFavourite(selectedPropertyId: String, isFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.toggleFavourite(selectedPropertyId, isFav)
        }
    }
}