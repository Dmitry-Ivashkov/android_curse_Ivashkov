package com.example.android_curse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.avatarInageView.setImageResource(R.mipmap.ic_launcher)
        Glide.with(holder.avatarInageView)
            .load(userList[position].avatarUrl)
            .circleCrop()
            .into(holder.avatarInageView)
        holder.userNameTextView.text = userList[position].userName
        holder.groupNameTextView.text = userList[position].groupName
    }

    override fun getItemCount() = userList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarInageView = itemView.findViewById<ImageView>(R.id.avatarImageView)
        val userNameTextView = itemView.findViewById<TextView>(R.id.userNameTextView)
        val groupNameTextView = itemView.findViewById<TextView>(R.id.groupNameTextView)
    }
}