package com.example.allinone;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ViewHeatblast extends Fragment {

    View view;
    RecyclerView recyclerView1;
    RelativeLayout coordinatorLayout1;
    ArrayList<Model> heatList;
    Database database;
    Adapter adapter;
    FloatingActionButton Faby1;
    Model model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enabling options menu in this fragment
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_heatblast2, container, false);

        Faby1 = (FloatingActionButton) view.findViewById(R.id.Faby1);
        coordinatorLayout1 = (RelativeLayout)view.findViewById(R.id.coordinatorlayout1);
        recyclerView1 = (RecyclerView)view.findViewById(R.id.recyclerview1);

        Faby1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).replaceFragments(AddHeatblast.class);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView1);

        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());

        // initializing our all variables.
        heatList = new ArrayList<>();

        // getting our course array list from db handler class.
        heatList = database.heatreadAllHeatblast();

        // on below line passing our array list to our adapter class.
        adapter = new Adapter(heatList, getActivity());
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview1);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerView1.setAdapter(adapter);
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
            Model item = adapter.getHeatList().get(position);
            adapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(coordinatorLayout1, "Item Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            adapter.restoreItem(item, position);
                            recyclerView1.scrollToPosition(position);
                        }
                    }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);

                            if(!(event == DISMISS_EVENT_ACTION)){
                                database.heatdeleteSingleHeatblast(item.getId());
                            }
                        }
                    });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //inflating are menu file
        inflater.inflate(R.menu.options_menu, menu);
        //below line toget our menu item
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        //getting searchview of our item
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Notes Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handling the menu item clicks
        int id = item.getItemId();

        if(id == R.id.actionDeleteAllHeatblast){
            database.heatdeleteAllHeatblast();
            heatList.clear();
            Toast.makeText(getActivity(), "All data deleted", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            recyclerView1.setAdapter(adapter);
        }
        if(id == R.id.actionSearch){
            Toast.makeText(getActivity(), "Search Data", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void refreshFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).addToBackStack(null).commit();
    }*/

    private void filter(String text){
        //creating a new arrafor filtering our data
        ArrayList<Model> heatfilter = new ArrayList<>();
        //running a for loop to match our data
        for(Model item : heatList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase()) || item.getDescription().toLowerCase().contains(text.toLowerCase())){
                heatfilter.add(item);
            }
        }
        if (heatfilter.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.heatfilter(heatfilter);
        }
    }

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