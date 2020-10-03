package com.riahi.usertasks.ui.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.riahi.usertasks.R
import com.riahi.usertasks.data.models.tasks.Tasks
import com.riahi.usertasks.databinding.ItemTasksListBinding

class TasksAdapter (val context: Context, val tasksList: List<Tasks>): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    override fun getItemCount(): Int = tasksList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksAdapter.TaskViewHolder {
        val binding: ItemTasksListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tasks_list, parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksAdapter.TaskViewHolder, position: Int) {
        val taskModel = tasksList[position]
        holder.bind(taskModel)
    }

    inner class TaskViewHolder(var itemRowView: ItemTasksListBinding) : RecyclerView.ViewHolder(itemRowView.root) {
        fun bind(task: Tasks) {
            itemRowView.dataTitle = task.title
            if (task.completed) {
                itemRowView.dataCompleted = "Terminé"
                itemRowView.completed.setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
            } else {
                itemRowView.dataCompleted = "Pas encore"
                itemRowView.completed.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
            }
            itemRowView.executePendingBindings()
        }
    }
}