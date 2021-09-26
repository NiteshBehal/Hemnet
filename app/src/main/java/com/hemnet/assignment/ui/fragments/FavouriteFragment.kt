package com.hemnet.assignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.hemnet.assignment.R
import com.hemnet.assignment.databinding.FragmentFavouriteBinding
import com.hemnet.assignment.di.ComponentFactory
import com.hemnet.assignment.di.fragment.ViewModelProviderFactory
import com.hemnet.assignment.ui.adapters.PropertyListAdapter
import com.hemnet.assignment.ui.viewmodels.FavouriteFragmentVM
import javax.inject.Inject

/**
 * Fragment class to display favourite marked items.
 */
class FavouriteFragment : Fragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private val viewModel: FavouriteFragmentVM by viewModels { providerFactory }
    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentFactory.createFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setObserversForData()
    }

    private fun setupUi() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.rvPropertyList.apply {
            adapter = PropertyListAdapter(
                listOf(),
                navigateToDetailPage(),
                toggleFav()
            )
        }
    }

    private fun setObserversForData() {
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.tvNoFav.visibility = VISIBLE
            } else {
                binding.tvNoFav.visibility = GONE
            }
            it?.let {
                (binding.rvPropertyList.adapter as PropertyListAdapter).updateList(
                    it
                )
            }
        }
    }

    private fun navigateToDetailPage() =
        { selectedPropertyId: String? ->
            val bundle = Bundle().apply {
                putString(SELECTED_PROPERTY_ID_KEY, selectedPropertyId)
            }
            Navigation.findNavController(binding.root)
                .navigate(
                    R.id.action_favouriteFragment_to_propertyDetailsFragment,
                    bundle
                )
        }

    private fun toggleFav() = { selectedPropertyId: String?, isFav: Boolean ->
        if (selectedPropertyId != null) {
            viewModel.toggleFavourite(selectedPropertyId, isFav)
        }
    }
}