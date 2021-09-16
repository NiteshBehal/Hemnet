package com.hemnet.assignment.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.databinding.ListItemPropertyBinding
import com.hemnet.assignment.ui.adapters.RowType

/**
 * PropertyViewHolder class to handle row element for Property type rendering
 */
class PropertyViewHolder(val binding: ListItemPropertyBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Property, toggleFav: (String?, Boolean) -> Unit) {
        binding.property = data
        if (data.type == RowType.HIGHLIGHTED_PROPERTY.type) {
            binding.ivHighlighter.visibility = View.VISIBLE
        } else {
            binding.ivHighlighter.visibility = View.GONE
        }
        binding.cbFav.setOnClickListener {
            toggleFav.invoke(data.id, data.isFav.not())
        }
    }

    companion object {
        fun create(parent: ViewGroup): PropertyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                ListItemPropertyBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return PropertyViewHolder(binding)
        }
    }
}