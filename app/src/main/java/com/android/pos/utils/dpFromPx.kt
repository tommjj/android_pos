package com.android.pos.utils

import android.content.Context

fun dpFromPx(context: Context, px: Int): Float {
    return px / context.resources.displayMetrics.density
}