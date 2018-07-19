package com.ctandem.basemvvm.view.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.ctandem.basemvvm.R
import com.ctandem.basemvvm.databinding.ActivityMainBinding
import com.ctandem.basemvvm.view.adapter.PostsAdapter
import com.ctandem.basemvvm.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        /*Binding object to hold views*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*ViewModel object to perform functions*/
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        /*mainViewModel is defined in xml to hold data*/
        binding.mainViewModel = viewModel

        /*Tell binding to be lifecycle aware of activity */
        binding.setLifecycleOwner(this)

        /* set activity layout*/
        setContentView(binding.root)

        setupPostRV()
    }

    private fun setupPostRV() {

        viewModel.getAllPosts().observe(this, Observer {

            if (it != null) {

                /*log list*/
                Log.e("", it.toString())

                adapter = PostsAdapter(
                        this,
                        it,
                        object : PostsAdapter.ClickListener {
                            override fun onRootClick(position: Int) {
                                viewModel.showPostDetail(position)
                            }
                        }
                )

                binding.postRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.postRv.itemAnimator = DefaultItemAnimator()
                binding.postRv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
                binding.postRv.isNestedScrollingEnabled = false
                binding.postRv.adapter = adapter

            }


        })

    }


}
