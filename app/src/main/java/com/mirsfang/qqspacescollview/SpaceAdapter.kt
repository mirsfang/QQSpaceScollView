package com.mirsfang.qqspacescollview

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * 作者： MirsFang on 2017/11/16 17:11
 * 邮箱： mirsfang@163.com
 * 类描述：
 */

class SpaceAdapter : RecyclerView.Adapter<ViewHolder>{


     var spaceItems: List<SpaceItem>;
     var recyclerView: RecyclerView;

    constructor(  spaceItems: List<SpaceItem>,  recyclerView: RecyclerView){
        this.spaceItems = spaceItems;
        this.recyclerView = recyclerView;
    }


    override fun getItemViewType(position: Int): Int {
        return spaceItems[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        var viewHolder: ViewHolder? = null
        if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_n, null)
            viewHolder = NormalViewHolder(view)
        } else if (viewType == 1) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_s, null)
            viewHolder = SpaceViewHolder(view)
        } else {
            Log.e("111","2222")
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (spaceItems[position].viewType == 0) {
            (holder as NormalViewHolder).textView.text = "item " + position
            if (position % 2 == 0) {
                holder.textView.setBackgroundResource(R.color.colorAccent)
            } else {
                holder.textView.setBackgroundResource(R.color.colorPrimary)
            }
        } else if (spaceItems[position].viewType == 1) {
            (holder as SpaceViewHolder).scrollChangeView.setRecyclerView(recyclerView)
        }

    }


    override fun getItemCount(): Int {
        return spaceItems.size
    }

    inner class NormalViewHolder(itemView: View) : ViewHolder(itemView) {
        internal var textView: TextView

        init {
            textView = itemView.findViewById(R.id.text)
        }
    }

    inner class SpaceViewHolder(itemView: View) : ViewHolder(itemView) {
        internal var scrollChangeView: ScrollChangeView

        init {
            scrollChangeView = itemView.findViewById(R.id.scroll)
        }
    }
}
