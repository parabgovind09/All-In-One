package com.example.allinone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class BAdapter extends RecyclerView.Adapter<BAdapter.ViewHolder>{
    // variable for our array list and context
    private ArrayList<BModel> hList;
    private Context context;

    //constructor
    public BAdapter(ArrayList<BModel> hList, Context context) {
        this.hList = hList;
        this.context = context;
    }

    //method for filtering our recyclerview data
    @SuppressLint("NotifyDataSetChanged")
    public void HfilterList(ArrayList<BModel> Flist){
        //below line is to add our filtered list in our course array list
        hList = Flist;
        //below line is to notify our BAdapter as change in recycler view data
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewheatblast_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data to our views of recycler view item.
        BModel modal = hList.get(position);
        holder.recyclerViewtv_title.setText(modal.getTitle().trim());
        holder.recyclerViewtv_description.setText(modal.getDescription().trim());
        holder.recyclerViewTextView1.setText(formatDate(modal.getCurrentdatetime()));

        holder.recyclerViewTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popupMenu = new PopupMenu(context,holder.recyclerViewTextView2);
                //inflating the menu from xml resource
                popupMenu.inflate(R.menu.navigationdrawer1);
                //adding click listeners
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navtodo:
                                Toast.makeText(menuItem.getActionView().getContext(), "You selected my account1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navarchive:
                                Toast.makeText(context.getApplicationContext(), "You selected my account1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navshared:
                                Toast.makeText(view.getContext(), "You selected todo1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navduplicate:
                                Toast.makeText(holder.recyclerViewTextView2.getContext(), "You selected my share1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navdeleted:
                                break;
                            case R.id.navsetting:
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popupMenu.show();
            }
        });


        boolean isExpanded = hList.get(position).isExpanded();
        holder.recyclerViewtv_description.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return hList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // creating variables for our text views.
        private TextView recyclerViewtv_title, recyclerViewtv_description, recyclerViewTextView1, recyclerViewTextView2;
        private RelativeLayout recyclerViewRelativeLayout;
        private ImageButton recyclerViewImageButton1;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            itemView.setClickable(true);

            recyclerViewtv_title = (TextView) itemView.findViewById(R.id.recyclerViewtv_title);
            recyclerViewtv_description = (TextView) itemView.findViewById(R.id.recyclerViewtv_description);
            recyclerViewTextView1 = (TextView) itemView.findViewById(R.id.recyclerViewTextView1);
            recyclerViewRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.recyclerViewRelativeLayout);
            recyclerViewImageButton1 = (ImageButton) itemView.findViewById(R.id.recyclerViewImageButton1);
            recyclerViewTextView2 = (TextView) itemView.findViewById(R.id.recyclerViewTextView2);

            recyclerViewtv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //transferData();
                            BModel modal = hList.get(getAdapterPosition());
                            modal.setExpanded(!modal.isExpanded());
                            notifyItemChanged(getAdapterPosition());
                        }
                    });
                }
            });

            recyclerViewtv_description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BModel modal = hList.get(getAdapterPosition());
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //transferData();
                        }
                    });
                }
            });

            recyclerViewImageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    transferData();
                }
            });
        }
        public void transferData(){
            ((MainActivity)context).setTitleData(hList.get(getAdapterPosition()).getTitle());
            ((MainActivity)context).setDescriptionData(hList.get(getAdapterPosition()).getDescription());
            ((MainActivity)context).setIdData(hList.get(getAdapterPosition()).getId());
            ((MainActivity)context).setCurrentDateTimeData(hList.get(getAdapterPosition()).getCurrentdatetime());
            ((MainActivity)context).setUpdatedatetime(hList.get(getAdapterPosition()).getUpdatedatetime());
            ((MainActivity)context).replaceFragments(BUpdateHeatblast.class);
        }
    }
    private String formatDate(String dateStr){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
            Date date = simpleDateFormat.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("E LLLL yyyy HH:mm:ss aaa");
            return fmtOut.format(date);
        }catch(ParseException e){

        }
        return "";
    }

    public ArrayList<BModel> gethList(){
        return hList;
    }

    public void removeItem(int position){
        hList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(BModel item, int position){
        hList.add(position, item);
        notifyItemInserted(position);
    }
}


