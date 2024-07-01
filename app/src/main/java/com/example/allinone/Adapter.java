package com.example.allinone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.drm.DrmStore;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.OnSwipe;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Model> heatList;
    private Context context;

    //constructor
    public Adapter(ArrayList<Model> heatList, Context context) {
        this.heatList = heatList;
        this.context = context;
    }

    //method for filtering our recyclerview data
    @SuppressLint("NotifyDataSetChanged")
    public void heatfilter(ArrayList<Model> heatfilter){
        //below line is to add our filtered list in our course array list
        heatList = heatfilter;
        //below line is to notify our adapter as change in recycler view data
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data to our views of recycler view item.
        Model modal = heatList.get(position);
        holder.title.setText(Html.fromHtml(modal.getTitle()));
        holder.description.setText(Html.fromHtml(modal.getDescription()));

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // below we are passing all our values to updateheatblast fragment.
                ((MainActivity)context).setTitledata(heatblastList.get(position).getTitle());
                ((MainActivity)context).setDescriptiondata(heatblastList.get(position).getDescription());
                ((MainActivity)context).setIddata(heatblastList.get(position).getId());
                ((MainActivity)context).replaceFragments(UpdateHeatblast.class);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return heatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // creating variables for our text views.
        private TextView title, description;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            itemView.setClickable(true);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);

            title.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(MotionEvent.ACTION_DOWN == motionEvent.getAction()){
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    else{
                        Model modal = heatList.get(getAdapterPosition());
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((MainActivity)context).setHtitledata(heatList.get(getAdapterPosition()).getTitle());
                                ((MainActivity)context).setHdescriptiondata(heatList.get(getAdapterPosition()).getDescription());
                                ((MainActivity)context).setHiddata(heatList.get(getAdapterPosition()).getId());
                                ((MainActivity)context).replaceFragments(UpdateHeatblast.class);
                            }
                        });
                    }
                    return false;
                }
            });

            description.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(MotionEvent.ACTION_DOWN == motionEvent.getAction()){
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    else{
                        Model modal = heatList.get(getAdapterPosition());
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((MainActivity)context).setHtitledata(heatList.get(getAdapterPosition()).getTitle());
                                ((MainActivity)context).setHdescriptiondata(heatList.get(getAdapterPosition()).getDescription());
                                ((MainActivity)context).setHiddata(heatList.get(getAdapterPosition()).getId());
                                ((MainActivity)context).replaceFragments(UpdateHeatblast.class);
                            }
                        });
                    }
                    return false;
                }
            });

            title.setMovementMethod(new ScrollingMovementMethod());
            description.setMovementMethod(new ScrollingMovementMethod());
        }
    }
    public ArrayList<Model> getHeatList(){
        return heatList;
    }

    public void removeItem(int position){
        heatList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Model item, int position){
        heatList.add(position, item);
        notifyItemInserted(position);
    }
}
