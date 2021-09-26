package com.hemnet.assignment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.data.repository.HemnetRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PropertyDetailsFragmentVM @Inject constructor(val repo: HemnetRepo) :
    ViewModel() {

    /**
     * Method to fetch property details from db on user selection
     */
    fun propertyDetails(id: String): LiveData<Property> {
        return repo.getPropertyDetails(id)
    }
    /**
     * Method to toggle Favourite status for properties
     */
    fun toggleFavourite(selectedPropertyId: String, isFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.toggleFavourite(selectedPropertyId, isFav)
        }

    }
}