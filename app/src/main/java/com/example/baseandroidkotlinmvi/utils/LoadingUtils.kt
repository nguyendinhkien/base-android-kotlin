package com.example.baseandroidkotlinmvi.utils

import android.content.Context
import com.example.baseandroidkotlinmvi.presentation.widgets.CustomLoader

class LoadingUtils {
    companion object {
        private var customLoader: CustomLoader? = null
        fun showLoading(context: Context?, isCancelable: Boolean) {
            hideLoading()
            if (context != null) {
                try {
                    customLoader = CustomLoader(context)

                    customLoader?.let { loader ->
                        loader.setCancelable(isCancelable)
                        loader.setCanceledOnTouchOutside(true)
                        loader.show()
                    }
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }
        }

        fun hideLoading() {
            if (customLoader != null && customLoader?.isShowing!!) {
                customLoader = try {
                    customLoader?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}