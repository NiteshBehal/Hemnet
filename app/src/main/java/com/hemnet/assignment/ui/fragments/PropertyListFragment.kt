package com.hemnet.assignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.hemnet.assignment.R
import com.hemnet.assignment.databinding.FragmentPropertyListBinding

import com.hemnet.assignment.di.ComponentFactory
import com.hemnet.assignment.di.fragment.ViewModelProviderFactory
import com.hemnet.assignment.ui.adapters.PropertyListAdapter
import com.hemnet.assignment.ui.viewmodels.PropertyListFragmentVM
import javax.inject.Inject

const val SELECTED_PROPERTY_ID_KEY = "SelectedPropertyId"
/**
 * Fragment class to display all property items list.
 */
class PropertyListFragment : Fragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private val viewModel: PropertyListFragmentVM by viewModels { providerFactory }
    private lateinit var binding: FragmentPropertyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentFactory.createFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentPropertyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setObserversForData()
    }

    private fun setupUi() {
        binding.rvPropertyList.apply {
            adapter = PropertyListAdapter(
                listOf(),
                navigateToDetailPage(),
                toggleFav()
            )
        }
        binding.toolbar.setOnMenuItemClickListener {
            Navigation.findNavController(binding.root)
                .navigate(
                    R.id.action_propertyListFragment_to_favouriteFragment
                )
            true
        }
    }

    private fun setObserversForData() {
        viewModel.propertyList.observe(viewLifecycleOwner) {
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
                    R.id.action_propertyListFragment_to_propertyDetailsFragment,
                    bundle
                )

        }

    private fun toggleFav() = { selectedPropertyId: String?, isFav: Boolean ->
        if (selectedPropertyId != null) {
            viewModel.toggleFavourite(selectedPropertyId, isFav)
        }
    }
}