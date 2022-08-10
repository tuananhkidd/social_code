package com.base.mvvmbasekotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.mvvmbasekotlin.R
import com.base.mvvmbasekotlin.base.adapter.RecyclerViewAdapter
import com.base.mvvmbasekotlin.entity.LoginType
import com.base.mvvmbasekotlin.extension.inflate
import kotlinx.android.synthetic.main.item_login_social.view.*

class LoginTypeAdapter(context: Context) : RecyclerViewAdapter(context,false) {
    override fun initNormalViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        return LoginTypeViewHolder(parent.inflate(R.layout.item_login_social))
    }

    override fun bindNormalViewHolder(holder: NormalViewHolder, position: Int) {
        val item = getItem(position,LoginType::class.java)
        holder.itemView.apply {
            item?.let {
                img_logo.setBackgroundResource(item.icon)
            }
        }
    }

    inner class LoginTypeViewHolder(view : View) : NormalViewHolder(view)
}