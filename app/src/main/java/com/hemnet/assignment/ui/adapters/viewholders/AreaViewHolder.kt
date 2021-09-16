package com.hemnet.assignment.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.databinding.ListItemAreaBinding

/**
 * AreaViewHolder class to handle row element for Area type rendering
 */
class AreaViewHolder(val binding: ListItemAreaBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Property) {
        binding.area = data
    }

    companion object {
        fun create(parent: ViewGroup): AreaViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                ListItemAreaBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return AreaViewHolder(binding)
        }
    }
}