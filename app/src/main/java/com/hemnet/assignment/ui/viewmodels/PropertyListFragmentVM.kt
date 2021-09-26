package com.hemnet.assignment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.data.repository.HemnetRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PropertyListFragmentVM @Inject constructor(val repo: HemnetRepo) :
    ViewModel() {

    /**
     * livedata to hold all Properties list data for ui
     */
//    val propertyList = liveData(Dispatchers.IO) {
//        repo.getPropertyListData().
//    }

    val propertyList = repo.getPropertyListData


    /**
     * Method to toggle Favourite status for properties
     */
    fun toggleFavourite(selectedPropertyId: String, isFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.toggleFavourite(selectedPropertyId, isFav)
        }

    }
}