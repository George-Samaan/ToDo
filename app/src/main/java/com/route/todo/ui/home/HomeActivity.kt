package com.route.todo.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.todo.R
import com.route.todo.databinding.ActivityHomeBinding
import com.route.todo.ui.home.addTask.AddTaskBottomSheet
import com.route.todo.ui.home.settings.SettingsFragment
import com.route.todo.ui.home.tasksList.TasksListFragments

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
                if (item.itemId == R.id.navigation_tasks)
                {
                    showFragment(TasksListFragments())
                }
                else
                    showFragment(SettingsFragment())

            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.navigation_tasks
        binding.fabAddTask.setOnClickListener{
            showAddTaskBottomSheet()
        }
    }

    private fun showAddTaskBottomSheet() {
         val addTaskSheet = AddTaskBottomSheet()
        addTaskSheet.onTaskAddedListener = AddTaskBottomSheet.OnTaskAddedListener {
            supportFragmentManager.fragments.forEach{
                fragment ->
                if(fragment is TasksListFragments && fragment.isAdded)
                    fragment.retreiveTasksList()
            }
        }
        addTaskSheet.show(supportFragmentManager,null)
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}