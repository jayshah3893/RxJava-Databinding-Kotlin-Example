package com.techflake.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techflake.R
import com.techflake.databinding.ItemGiphyThumbBinding
import com.techflake.network.model.PreviewWebp
import com.techflake.ui.base.MainActivity
import com.techflake.utils.Constants.loadGif


class GiphyVideoAdapter(private val aContext: Context, private val postList: List<PreviewWebp>) :
    RecyclerView.Adapter<GiphyVideoAdapter.MyViewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    inner class MyViewHolder(val binding: ItemGiphyThumbBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding =
            DataBindingUtil.inflate<ItemGiphyThumbBinding>(layoutInflater!!, R.layout.item_giphy_thumb, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.preview = postList[position]
        loadGif(holder.binding.preview!!.url!!, holder.binding.ivThumnail)
        holder.itemView.setOnClickListener {
            (aContext as MainActivity).goToVideoView(position)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}