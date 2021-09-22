package com.example.android_curse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

var userList : StateFlow<List<User>> = flow<List<User>> {  }.stateIn(GlobalScope, SharingStarted.Eagerly, listOf()) //TODO(Scope)

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.avatarInageView)
            .load(userList.value[position].avatarUrl)
            .circleCrop()
            .into(holder.avatarInageView)
        holder.userNameTextView.text = userList.value[position].userName
        holder.groupNameTextView.text = userList.value[position].groupName
    }

    override fun getItemCount() = userList.value.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarInageView = itemView.findViewById<ImageView>(R.id.avatarImageView)
        val userNameTextView = itemView.findViewById<TextView>(R.id.userNameTextView)
        val groupNameTextView = itemView.findViewById<TextView>(R.id.groupNameTextView)
    }
}