package com.example.allinone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

    private ArrayList<RModel> heatblastList;
    private Context context;

    public RAdapter(ArrayList<RModel> heatblastList, Context context) {
        this.heatblastList = heatblastList;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<RModel> filterlist){
        heatblastList = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_reminder_file, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RModel modal = heatblastList.get(position);
        holder.title.setText(modal.getTitle());
        holder.description.setText(modal.getDescription());
        holder.date.setText(modal.getDate());
        holder.time.setText(modal.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).setClocktitledata(heatblastList.get(position).getTitle());
                ((MainActivity)context).setClockdescriptiondata(heatblastList.get(position).getDescription());
                ((MainActivity)context).setClockdatedata(heatblastList.get(position).getDate());
                ((MainActivity)context).setClocktimedata(heatblastList.get(position).getTime());
                ((MainActivity)context).setClockiddata(heatblastList.get(position).getId());
                ((MainActivity)context).setClockuniddata(heatblastList.get(position).getUniid());
                ((MainActivity)context).replaceFragments(UpdateReminder.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return heatblastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title, description, date, time;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);

            title = (TextView) itemView.findViewById(R.id.rcv_title);
            description = (TextView) itemView.findViewById(R.id.rcv_description);
            date = (TextView) itemView.findViewById(R.id.rcv_date);
            time = (TextView) itemView.findViewById(R.id.rcv_time);

        }
    }
    public ArrayList<RModel> getHeatblastList(){
        return heatblastList;
    }

    public void removeItem(int position){
        heatblastList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(RModel item, int position){
        heatblastList.add(position, item);
        notifyItemInserted(position);
    }
}