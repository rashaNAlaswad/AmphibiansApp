package com.rns.amphibiansapplication.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rns.amphibiansapplication.R
import com.rns.amphibiansapplication.network.Amphibian
import com.rns.amphibiansapplication.ui.AmphibianApiStatus
import com.rns.amphibiansapplication.ui.home.AmphibianListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Amphibian>?) {
    var adapter = recyclerView.adapter as AmphibianListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(imageView: ImageView, status: AmphibianApiStatus) {
    when (status) {
        AmphibianApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }

        AmphibianApiStatus.DONE -> {
            imageView.visibility = View.GONE
        }

        AmphibianApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }

}
