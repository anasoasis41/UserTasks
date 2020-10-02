package com.riahi.usertasks.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.riahi.usertasks.R
import com.riahi.usertasks.data.models.Users
import com.riahi.usertasks.databinding.ItemUserListBinding
import timber.log.Timber

class UsersRecyclerAdapter(val usersList: List<Users>,
                           val context: Context):
    RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>(), UserItemClickListener
{
    override fun getItemCount(): Int = usersList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersRecyclerAdapter.ViewHolder {
        val binding: ItemUserListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersRecyclerAdapter.ViewHolder, position: Int) {
        val userModel = usersList[position]
        holder.bind(userModel)
        holder.itemRowView.itemClickListener = this
    }

    inner class ViewHolder(var itemRowView: ItemUserListBinding) : RecyclerView.ViewHolder(itemRowView.root) {
        fun bind(user: Users) {
            itemRowView.model = user
            itemRowView.executePendingBindings()
        }
    }

    override fun onUserClicked(id: Int) {
        Toast.makeText(context,"userID: $id", Toast.LENGTH_SHORT).show()
    }
}