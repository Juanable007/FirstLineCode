package com.example.activitytest1

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {


    init {
        LayoutInflater.from(context).inflate(R.layout.activity_top_layout, this)
    }


}