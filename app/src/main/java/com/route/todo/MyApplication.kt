package com.route.todo

import android.app.Application
import com.route.todo.database.MyDataBase

class MyApplication:Application () {

    override fun onCreate() {
        super.onCreate()
        //initialize

        MyDataBase.init(this)
    }
}