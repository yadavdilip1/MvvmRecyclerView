package com.assesment.mvvmrecyclerview.utils

import android.app.Activity
import android.net.Uri
import android.view.View
import com.assesment.mvvmrecyclerview.R
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Activity.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun SimpleDraweeView.loadImage(url: String) {
    this.setImageURI(Uri.parse(url))
}


fun Activity.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> snackbar(
            "Please check your internet connection",
            retry
        )
        else -> {
            val error = failure.errorBody?.string().toString()
            snackbar(error)
        }
    }
}
