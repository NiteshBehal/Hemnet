package com.hemnet.assignment.ui.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hemnet.assignment.R


/**
 * BindingAdapter to load image to Image view directly from DataBinding xml
 */
@BindingAdapter("app:loadImage")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.default_img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}

