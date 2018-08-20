package com.ctandem.basemvvm.service.callbacks

import android.support.annotation.StringRes
import com.ctandem.basemvvm.R

enum class EmailErrorEvent(@StringRes private val resourceId: Int) : ErrorEvent {
    NONE(0),
    NO_DATA(R.string.no_data_found),
    SERVER_ERROR(R.string.server_error);

    override fun getErrorResource() = resourceId
}