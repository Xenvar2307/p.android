package com.example.todolist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<TasksViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list task
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list tasks to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val TasksViewModel = mList[position]

        // sets the image to the imageview from our taskHolder class
        holder.imageView.setImageResource(TasksViewModel.image)

        // sets the text to the textview from our taskHolder class
        holder.textView.text = TasksViewModel.text

    }

    // return the number of the tasks in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(TaskView: View) : RecyclerView.ViewHolder(TaskView) {
        val imageView: ImageView = TaskView.findViewById(R.id.imageview)
        val textView: TextView = TaskView.findViewById(R.id.textView)
    }
}
