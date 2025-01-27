package com.route.todo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todo.database.model.Task


@Dao

interface TasksDao {

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("select * from task")
    fun getAllTasks(): List<Task>

    @Query("select *  from task where date = :date order by time ASC")
    fun geTasksByDate(date: Long): List<Task>
}