package com.ctandem.basemvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import com.ctandem.basemvvm.service.model.Post
import com.ctandem.basemvvm.service.repository.Repository
import com.ctandem.basemvvm.view.ui.activities.DetailActivity


/**
 * Created by Zaeem Sattar on 7/6/2018.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    /*this app instance is from AndroidViewModel, we will save it for later use*/
    var mApplication = application

    /*
    * this will be called on ViewModel Creation time
    * */
    private var mRepository: Repository = Repository(application)

    /*
   * this will be called on ViewModel Creation time and this
   * will hold UI data for Main screen
   * */
    private var mAllPosts: LiveData<List<Post>> = MutableLiveData<List<Post>>()

    fun getAllPosts(): LiveData<List<Post>> {
        /*
        * List to return in activity for observation
        * When DB is empty this will get updated on callback and send notify to View
        * */
        mAllPosts = mRepository.allPosts

        return mAllPosts
    }

    fun showPostDetail(pos: Int) {
        /*
        * start activity from context of application with
        * any data needed to be shared in next activity
        * */
        val mIntent = Intent(mApplication, DetailActivity::class.java)
        mIntent.putExtra("data", mAllPosts.value!![pos])
        mApplication.startActivity(mIntent)

    }

}