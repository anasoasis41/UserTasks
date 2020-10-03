package com.riahi.usertasks.data.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.riahi.usertasks.WEB_SERVICE_URL
import com.riahi.usertasks.data.UsersDatabase
import com.riahi.usertasks.data.models.tasks.Tasks
import com.riahi.usertasks.data.services.TasksService
import com.riahi.usertasks.networkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class TasksRepository(val context: Context, val userId: Int) {

    val tasksData = MutableLiveData<List<Tasks>>()
    private val tasksDao = UsersDatabase.getDatabase(context).tasksDao()

    /*init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = tasksDao.getAllTasks()
            Timber.i("local data ${data.size}")
            if (tasksDao.isTaskExist(userId)) {

                tasksData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,"Exist",Toast.LENGTH_SHORT).show()
                    //Toast.makeText(context,"Using local data",Toast.LENGTH_SHORT).show()
                    Timber.i("Using local data")
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,"Not Exist",Toast.LENGTH_SHORT).show()
                }

                callTasksService(userId)
            }
            /*for (task in data) {
                Timber.i("local id ${task.userId}")
                if (task.userId == userId) {
                    tasksData.postValue(data)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context,"Using local data",Toast.LENGTH_SHORT).show()
                        Timber.i("Using local data")
                    }
                } else {
                    callTasksService(userId)
                }
            }*/

        }
    }*/

    suspend fun getTasks(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = tasksDao.getTaskById(userId)
            Timber.i("local data ${data.size}")
            if (tasksDao.isTaskExist(userId)) {

                tasksData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Exist", Toast.LENGTH_SHORT).show()
                    //Toast.makeText(context,"Using local data",Toast.LENGTH_SHORT).show()
                    Timber.i("Using local data")
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Not Exist", Toast.LENGTH_SHORT).show()
                }

                callTasksService(userId)
            }
        }
    }

    @WorkerThread
    suspend fun callTasksService(userId: Int) {
        if (networkAvailable(context)) {
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(TasksService::class.java)
            val serviceData = service.getUserTasks(userId).body() ?: emptyList()
            tasksData.postValue(serviceData)
            // Save data in room database
            //tasksDao.deleteAllTasks()
            tasksDao.insertTasks(serviceData)
        }
    }

    fun tasksDataFromWeb(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            callTasksService(userId)
        }
    }

    @Suppress("DEPRECATION")
    fun networkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

}