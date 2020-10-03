package com.riahi.usertasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.riahi.usertasks.data.dao.TasksDao
import com.riahi.usertasks.data.dao.UsersDoa
import com.riahi.usertasks.data.models.tasks.Tasks
import com.riahi.usertasks.data.models.users.Users

@Database(entities = [Users::class, Tasks::class], version = 3, exportSchema = false)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun usersDao(): UsersDoa
    abstract fun tasksDao(): TasksDao

    companion object {
        // This object can be accessed by more than one thread at the time
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UsersDatabase::class.java,
                        "users_tasks.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}