package com.hemnet.assignment.ui.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemnet.assignment.data.models.Property
import com.hemnet.assignment.ui.adapters.RowType.AREA
import com.hemnet.assignment.ui.adapters.RowType.PROPERTY
import com.hemnet.assignment.ui.adapters.viewholders.AreaViewHolder
import com.hemnet.assignment.ui.adapters.viewholders.PropertyViewHolder

/***
 * RecyclerView adapter class to display Properties List and Favourite list
 *
 * It is currently handling two types of views.
 * 1. Property View
 * 2. Area View
 */
class PropertyListAdapter(
    private var propertyList: List<Property>,
    private val propertyClick: (String?) -> Unit,
    private val toggleFav: (String?, Boolean) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return when (viewType) {
            AREA.ordinal -> AreaViewHolder.create(parent)
            else -> PropertyViewHolder.create(parent)
        }
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = propertyList.get(position)
        when (holder) {
            is PropertyViewHolder -> {
                holder.bind(item, toggleFav)
                holder.itemView.setOnClickListener {
                    propertyClick.invoke(item.id)
                }
            }
            is AreaViewHolder -> {
                holder.bind(item)
                holder.itemView.setOnClickListener(null)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (propertyList.get(position).type == AREA.type) {
            AREA.ordinal
        } else {
            PROPERTY.ordinal
        }
    }

    fun updateList(propertyList: List<Property>) {
        this.propertyList = propertyList
        notifyDataSetChanged()
    }
}