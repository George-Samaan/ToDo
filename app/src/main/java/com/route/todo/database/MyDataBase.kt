package com.route.todo.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todo.database.dao.TasksDao
import com.route.todo.database.model.Task

@Database(entities = [Task::class], version = 2, exportSchema = true)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    //static objects in kotlin by creating companion objects
    companion object {
        private const val DATABASE_NAME = "Tasks Database"
        private var database: MyDataBase? = null

        fun init(app: Application) {
            database = Room.databaseBuilder(
                app.applicationContext,
                MyDataBase::class.java,
                DATABASE_NAME
            )
                //  .addMigrations()              ??????
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        fun getInstance(): MyDataBase {
            return database!!
        }
    }
}
