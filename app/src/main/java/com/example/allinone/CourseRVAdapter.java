package com.example.allinone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;

    // constructor
    public CourseRVAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    //method for filtering our recyclerview data
    public void filterList(ArrayList<CourseModal> filterlist){
        //below line is to add our filtered list in our course array list
        courseModalArrayList = filterlist;
        //below line is to notify our RAdapter as change in recycler view data
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CourseModal modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());

        // below line is to add on click listener for our recycler view item.

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below we are passing all our values.

                ((MainActivity)context).setName(courseModalArrayList.get(position).getCourseName());
                ((MainActivity)context).setDescription(courseModalArrayList.get(position).getCourseDescription());
                ((MainActivity)context).replaceFragments(UpdateCourses.class);

            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // creating variables for our text views.
        private TextView courseNameTV, courseDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseNameTV = (TextView) itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = (TextView) itemView.findViewById(R.id.idTVCourseDescription);
        }
    }
    public ArrayList<CourseModal> getCourseModalArrayList(){
        return courseModalArrayList;
    }

    public void removeItem(int position){
        courseModalArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(CourseModal item, int position){
        courseModalArrayList.add(position, item);
        notifyItemInserted(position);
    }
}

