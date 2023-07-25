package com.example.githuprepo.presentation.bindingAdapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("loadImageRes")
fun loadImage(imageView: ImageView,drawableRes: Drawable) {
    Glide.with(imageView.context)
        .load(drawableRes)
        .into(imageView)
}

@BindingAdapter("loadImageRes")
fun loadImage(imageView: ShapeableImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }

}


//@BindingAdapter("imageUrl", "error")
//fun loadImage(view: ImageView, url: String, error: Drawable) {
//    Picasso.get().load(url).error(error).into(view)
//}
