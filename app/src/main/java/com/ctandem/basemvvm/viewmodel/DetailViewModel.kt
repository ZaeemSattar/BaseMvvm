package com.ctandem.basemvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.ctandem.basemvvm.service.model.Post

/**
 * Created by Zaeem Sattar on 7/19/2018.
 */
class DetailViewModel(application: Application) : AndroidViewModel(application) {

    var mPost = Post()

}