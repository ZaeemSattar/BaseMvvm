package com.ctandem.basemvvm.service.callbacks

import android.support.annotation.StringRes

interface ErrorEvent {
    @StringRes
    fun getErrorResource(): Int
}