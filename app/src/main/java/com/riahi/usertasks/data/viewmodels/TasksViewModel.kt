package com.riahi.usertasks.data.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riahi.usertasks.data.repositories.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class TasksViewModel(context: Context,userId: Int) : ViewModel() {

    val tasksRepository = TasksRepository(context,userId)
    val tasksListData = tasksRepository.tasksData

    init {
        Timber.i( "userIdModel $userId")
    }

    fun getUserTasks(id: Int) {
        val job = Job()
        val coroutineScope = CoroutineScope(job + Dispatchers.Main)
        coroutineScope.launch {
            tasksRepository.getTasks(id)
        }

    }
}

class TasksViewModelFactory(private val context: Context, private val userId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TasksViewModel(context, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}