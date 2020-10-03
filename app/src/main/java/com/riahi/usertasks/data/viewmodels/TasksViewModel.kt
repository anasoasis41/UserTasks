package com.riahi.usertasks.data.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riahi.usertasks.data.repositories.TasksRepository

class TasksViewModel(context: Context,userId: Int) : ViewModel() {

    val tasksRepository = TasksRepository(context,userId)
    val tasksListData = tasksRepository.tasksData

}

class TasksViewModelFactory(private val context: Context, private val userId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TasksViewModel(context, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}