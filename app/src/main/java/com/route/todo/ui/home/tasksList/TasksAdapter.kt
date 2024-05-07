package com.route.todo.ui.home.tasksList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.todo.database.model.Task
import com.route.todo.databinding.ItemTaskBinding

class TasksAdapter(var tasks: MutableList<Task>? = null) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: ItemTaskBinding =
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = tasks?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks!![position]
        holder.bind(task)
        holder.itemView.setOnClickListener {
            onTaskClickListener?.onTaskClicked(task)
        }
    }

    fun changeData(allTasks: List<Task>) {
        if (tasks == null)
            tasks = mutableListOf()
        tasks?.clear()
        tasks?.addAll(allTasks)
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.title.text = task.title
            binding.description.text = task.content
        }
    }


    private var onTaskClickListener: OnTaskClickListener? = null

    fun setOnTaskClickListener(listener: OnTaskClickListener) {
        onTaskClickListener = listener
    }

    fun interface OnTaskClickListener {
        fun onTaskClicked(task: Task)

    }
}