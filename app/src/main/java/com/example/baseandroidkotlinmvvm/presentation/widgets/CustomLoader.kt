package com.example.baseandroidkotlinmvvm.presentation.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.LinearLayout
import com.example.baseandroidkotlinmvvm.R

class CustomLoader(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_loader_layout)
        window?.let {
            it.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}