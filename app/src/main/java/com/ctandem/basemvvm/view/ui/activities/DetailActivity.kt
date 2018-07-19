package com.ctandem.basemvvm.view.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ctandem.basemvvm.R
import com.ctandem.basemvvm.databinding.ActivityDetailBinding
import com.ctandem.basemvvm.viewmodel.DetailViewModel

/**
 * Created by Zaeem Sattar on 7/19/2018.
 */
class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel


    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        viewModel.mPost= intent.getParcelableExtra("data")

        binding.detailViewModel=viewModel

        binding.setLifecycleOwner(this)

        setContentView(binding.root)

        setupUI()

    }

    private fun setupUI() {

    }
}