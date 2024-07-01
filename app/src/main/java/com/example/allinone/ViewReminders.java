package com.example.allinone;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ViewReminders extends Fragment {

    View view;
    FloatingActionButton view_fab;
    RecyclerView recyclerView1;
    CoordinatorLayout coordinatorLayout1;
    ArrayList<RModel> heatblastList;
    Database database;
    RAdapter RAdapter;
    RModel RModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_reminders, container, false);
        view_fab = (FloatingActionButton) view.findViewById(R.id.view_fab);
        coordinatorLayout1 = (CoordinatorLayout)view.findViewById(R.id.coordinatorLayout1);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.view_rcv);

        view_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).replaceFragments(AddReminder.class);
            }
        });

        database = new Database(getActivity());

        heatblastList = new ArrayList<>();

        heatblastList = database.readAllHeatblast();

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView1);

        RAdapter = new RAdapter(heatblastList, getActivity());
        recyclerView1 = (RecyclerView)view.findViewById(R.id.view_rcv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);

        recyclerView1.setAdapter(RAdapter);
        return view;
    }
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            RModel item = RAdapter.getHeatblastList().get(position);
            RAdapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(coordinatorLayout1, "Item Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RAdapter.restoreItem(item, position);
                            recyclerView1.scrollToPosition(position);
                        }
                    }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);

                            if(!(event == DISMISS_EVENT_ACTION)){
                                AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
                                Intent intent = new Intent(getActivity(), AlarmBrodcast.class);
                                @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), item.getUniid(), intent, 0);
                                alarmManager.cancel(pendingIntent);
                                pendingIntent.cancel();
                                Toast.makeText(getActivity(),"Hello"+item.getId(),Toast.LENGTH_SHORT).show();
                                database.deleteSingleHeatblast(item.getId());
                                //database.deleteAllHeatblast();
                                RAdapter.notifyDataSetChanged();
                            }
                        }
                    });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    };

    //handling on back button pressed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) requireActivity()).replaceFragments(BViewHeatblast.class);
            }
        });
    }
}