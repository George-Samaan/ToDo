package com.route.todo.ui.home.tasksList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todo.Constants
import com.route.todo.R
import com.route.todo.database.MyDataBase
import com.route.todo.databinding.FragmentTasksBinding
import com.route.todo.ui.home.editTask.EditTaskFragment
import java.util.Calendar

class TasksListFragments : Fragment() {

    lateinit var binding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()

    }

    override fun onResume() {
        super.onResume()
        retreiveTasksList()
    }


    val currentDate = Calendar.getInstance()
    fun retreiveTasksList() {
        val allTasks = MyDataBase.getInstance()
            .getTasksDao()
            .geTasksByDate(currentDate.getDateOnly())
        adapter.changeData(allTasks)
    }

    val adapter = TasksAdapter()
    private fun setUpViews() {
        // to go to another fragment
//        adapter.setOnTaskClickListener{task ->
//            if (activity == null)return@setOnTaskClickListener
//            //sending object
//            val fragment = EditTaskFragment()
//            val bundle  = Bundle()
//            bundle.putParcelable(Constants.PASSED_TASK,task)
//            fragment.arguments = bundle
//            parentFragmentManager.beginTransaction().replace(
//                R.id.fragment_container,fragment
//            ).commit()
//        }
        binding.rvTasks.adapter = adapter
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected)
                currentDate.set(date.year, date.month - 1, date.day)
            retreiveTasksList()
        }
    }


    fun Calendar.getDateOnly(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(
            get(Calendar.YEAR),
            get(Calendar.MONTH),
            get(Calendar.DATE),
            0,
            0,
            0
        )
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time.time
    }
}