package com.route.todo.ui.home.editTask

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.todo.Constants
import com.route.todo.database.model.Task
import com.route.todo.databinding.FragmentEditTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale


class EditTaskFragment : Fragment() {

    private lateinit var binding: FragmentEditTaskBinding
    private lateinit var task: Task


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditTaskBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        getPassedTask()
//        bindTask(task)
    }

//    private fun getPassedTask() {
//        arguments?.let {
//            task = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                requireArguments().getParcelable(
//                    Constants.PASSED_TASK, Task::class.java
//                ) ?: Task()
//            } else {
//                requireArguments().getParcelable(
//                    Constants.PASSED_TASK
//                ) ?: Task()
//            }
//        }
//    }
//
//    private fun bindTask(task: Task) {
//        val dateFormat = SimpleDateFormat("dd/MM/yyyy" , Locale.getDefault())
//        val timeFormat = SimpleDateFormat("HH:mm:ss" , Locale.getDefault())
//        binding.title.setText(task.title.toString())
//        binding.describition.setText(task.content.toString())
//        // Formatting date
//        val date = dateFormat.format(task.date)
//        binding.selectDateTv.text = date ?: ""
//        // Formatting time
//        val time = timeFormat.format(task.time)
//        binding.selectTimeTv.text = time ?: ""
//        binding.isDoneCheck.isChecked = task.isDone ?: false
//    }
//
//    private fun saveTask() {
//        val calendar = Calendar.getInstance()
//
//        val newTask : Task(
//            task.id,
//            binding.title.text.toString(),
//            binding.describition.text.toString(),
//            binding.date,
//            binding.isDoneCheck.isChecked
//
//
//        )
//    }


}