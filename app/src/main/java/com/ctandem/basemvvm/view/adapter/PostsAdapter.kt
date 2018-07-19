package com.ctandem.basemvvm.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ctandem.basemvvm.R
import com.ctandem.basemvvm.databinding.RowPostBinding
import com.ctandem.basemvvm.service.model.Post

/**
 * Created by Zaeem Sattar on 1/5/2018.
 */
class PostsAdapter(private var context: Context, private var items: List<Post>, private var listener: ClickListener) : RecyclerView.Adapter<PostsAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val binding = DataBindingUtil.inflate<RowPostBinding>(
                LayoutInflater.from(context),
                R.layout.row_post,
                parent,
                false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.binding.post = items[position]
        holder.rootClick(position, listener)

    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    class MyHolder constructor(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun rootClick(position: Int, listener: ClickListener) {
            binding.root.setOnClickListener {
                listener.onRootClick(position)
            }
        }
    }

    interface ClickListener {
        fun onRootClick(position: Int)
    }
}