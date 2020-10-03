package com.riahi.usertasks.data.repositories

import android.app.Application
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.riahi.usertasks.WEB_SERVICE_URL
import com.riahi.usertasks.data.UsersDatabase
import com.riahi.usertasks.data.models.users.Users
import com.riahi.usertasks.data.services.UserService
import com.riahi.usertasks.networkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class UsersRepository(val app: Application) {

    val userData = MutableLiveData<List<Users>>()
    private val userDao = UsersDatabase.getDatabase(app).usersDao()

    init {
        // Background Thread
        CoroutineScope(Dispatchers.IO).launch {
            // Get all the data from database
            val data = userDao.getAllUsers()
            if (data.isEmpty()) {
                callWebService()
            } else {
                userData.postValue(data)
                // Foreground Thread
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "No internet connection", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable(app)) {
            withContext(Dispatchers.Main) {
                Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
            }
            Timber.i("Calling web service")
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(UserService::class.java)
            val serviceData = service.getUserData().body() ?: emptyList()
            userData.postValue(serviceData)
            // Save data in room database
            userDao.deleteAllUsers()
            userDao.insertUsers(serviceData)
        }
    }

    fun usersDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

}