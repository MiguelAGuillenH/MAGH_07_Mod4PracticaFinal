package com.magh.magh_07_mod4practicafinal

import android.text.style.ClickableSpan
import android.view.View

class ClickSpan(private var listener: View.OnClickListener) : ClickableSpan() {

    override fun onClick(view: View) {
        listener.onClick(view)
    }

}