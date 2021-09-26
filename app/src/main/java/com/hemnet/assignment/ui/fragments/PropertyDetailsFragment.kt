package com.hemnet.assignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hemnet.assignment.databinding.FragmentPropertyDetailsBinding
import com.hemnet.assignment.di.ComponentFactory
import com.hemnet.assignment.di.fragment.ViewModelProviderFactory
import com.hemnet.assignment.ui.adapters.RowType
import com.hemnet.assignment.ui.viewmodels.PropertyDetailsFragmentVM
import javax.inject.Inject

/**
 * Fragment class to display Property Details.
 */
class PropertyDetailsFragment : Fragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private val viewModel: PropertyDetailsFragmentVM by viewModels { providerFactory }
    private lateinit var binding: FragmentPropertyDetailsBinding
    private var selectedPropertyId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentFactory.createFragmentComponent().inject(this)
        arguments?.let {
            selectedPropertyId = it.getString(SELECTED_PROPERTY_ID_KEY, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentPropertyDetailsBinding.inflate(inflater, container, false)
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
        binding.cbFav.setOnClickListener {
            viewModel.toggleFavourite(
                selectedPropertyId,
                binding.property?.isFav?.not() ?: false
            )
        }
    }

    private fun setObserversForData() {
        viewModel.propertyDetails(selectedPropertyId)
            .observe(viewLifecycleOwner) {
                it?.let {
                    binding.property = it
                    if (it.type == RowType.HIGHLIGHTED_PROPERTY.type) {
                        binding.ivHighlighter.visibility = View.VISIBLE
                    } else {
                        binding.ivHighlighter.visibility = View.GONE
                    }
                }
            }
    }
}