package com.example.githuprepo.presentation.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("visibleGone")
fun visibleGone(view: View, isVisible: Boolean) {
    if (isVisible){
        view.visibility = View.VISIBLE
    }else {
        view.visibility = View.GONE
    }

}