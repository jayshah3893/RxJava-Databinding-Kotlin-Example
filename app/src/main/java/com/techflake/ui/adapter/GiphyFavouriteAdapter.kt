package com.techflake.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techflake.R
import com.techflake.databinding.ItemGiphyThumbFavouriteBinding
import com.techflake.objectbox.StoredGiphyClass
import com.techflake.utils.Constants.loadGif


class GiphyFavouriteAdapter(private val postList: List<StoredGiphyClass>) :
    RecyclerView.Adapter<GiphyFavouriteAdapter.MyViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    inner class MyViewHolder(val binding: ItemGiphyThumbFavouriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding =
            DataBindingUtil.inflate<ItemGiphyThumbFavouriteBinding>(layoutInflater!!, R.layout.item_giphy_thumb_favourite, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.preview = postList[position]
        loadGif(holder.binding.preview!!.mThumb, holder.binding.ivThumnail)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}