package com.route.todo.ui.home.addTask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todo.database.MyDataBase
import com.route.todo.database.model.Task
import com.route.todo.databinding.FragmentAddTaskBinding
import com.route.todo.ui.formatDate
import com.route.todo.ui.formatTime
import com.route.todo.ui.getDateOnly
import com.route.todo.ui.getTimeOnly
import com.route.todo.ui.home.showDialog

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()

    }

    private fun setUpViews() {
        binding.addTaskBtn.setOnClickListener {
            addTask()
        }
        binding.selectDateTil.setOnClickListener {
            showDatePicker()
        }
        binding.selectTimeTil.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(
            requireContext(),
            { view, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                binding.selectTimeTv.text = calendar.formatTime()
                binding.selectTimeTil.error = null
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePicker.show()
    }

    val calendar = Calendar.getInstance()

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(requireContext())
        datePicker.setOnDateSetListener(
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)

                binding.selectDateTv.text = calendar.formatDate()
                binding.selectDateTil.error = null

            }
        )
        datePicker.show()

    }

    private fun isValidateTaskInput(): Boolean {
        var isValid = true
        val title = binding.title.text.toString()
        val describition = binding.describition.text.toString()

        if (title.isBlank()) {
            binding.titleTil.error = "Please enter Task title"
            isValid = false
        } else {
            binding.titleTil.error = null
        }

        if (describition.isBlank()) {
            binding.descriptionTil.error = "Please enter Description content  "
            isValid = false
        } else {
            binding.descriptionTil.error = null
        }
        if (binding.selectTimeTv.text.isBlank()) {
            binding.selectTimeTil.error = "Please select time"
            isValid = false
        }
        if (binding.selectDateTv.text.isBlank()) {
            isValid = false
            binding.selectDateTil.error = "Please select date"
        }
        return isValid
    }

    private fun addTask() {
        if (!isValidateTaskInput())
            return
        MyDataBase.getInstance().getTasksDao().insertTask(
            Task(
                id,
                title = binding.title.text.toString(),
                content = binding.describition.text.toString(),
                date = calendar.getDateOnly(),
                time = calendar.getTimeOnly()
            )
        )
        showDialog(
            "Task Inserted successfully",
            "ok",
            posActionCallBack = {
                dismiss()
                onTaskAddedListener?.onTaskAdd()
            },
            isCancelable = false
        )
    }


    var onTaskAddedListener: OnTaskAddedListener? = null

    fun interface OnTaskAddedListener {
        fun onTaskAdd()
    }

}