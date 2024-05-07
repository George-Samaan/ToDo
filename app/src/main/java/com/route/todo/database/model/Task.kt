package com.route.todo.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Task(

    @PrimaryKey(true)
    val id: Int,

    @ColumnInfo
    val title: String? = null,

    @ColumnInfo
    val content: String? = null,

    @ColumnInfo
    val isDone: Boolean? = false,

    @ColumnInfo
    val date: Long? = null,

    @ColumnInfo
    val time: Long? = null
) : Parcelable