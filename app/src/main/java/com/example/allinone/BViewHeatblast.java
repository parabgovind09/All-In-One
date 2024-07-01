package com.example.allinone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class BViewHeatblast extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    View view;
    public DrawerLayout viewHeatblastDrawerLayout;
    public ActionBarDrawerToggle viewHeatblastActionBarDrawerToggle;
    CoordinatorLayout viewHeatblastCoordinatorLayout;
    FloatingActionButton viewHeatblastFab1, viewHeatblastFab2, viewHeatblastFab3;
    TextView viewHeatblasttv_todo, viewHeatblasttv_reminder;
    RecyclerView viewHeatblastRecyclerView;
    ArrayList<BModel> hList;
    Database database;
    BAdapter BAdapter;
    BModel BModel;
    Boolean isAllFabsVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        this.setHasOptionsMenu(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_heatblast, container, false);
        viewHeatblastCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.viewHeatblastCoordinatorLayout);
        viewHeatblastFab1 = (FloatingActionButton) view.findViewById(R.id.viewHeatblastFab1);
        viewHeatblastFab2 = (FloatingActionButton) view.findViewById(R.id.viewHeatblastFab2);
        viewHeatblastFab3 = (FloatingActionButton) view.findViewById(R.id.viewHeatblastFab3);
        viewHeatblasttv_todo = (TextView) view.findViewById(R.id.viewHeatblasttv_todo);
        viewHeatblasttv_reminder = (TextView) view.findViewById(R.id.viewHeatblasttv_reminder);
        viewHeatblastRecyclerView = (RecyclerView) view.findViewById(R.id.viewHeatblastRecyclerView);
        viewHeatblastDrawerLayout = (DrawerLayout) view.findViewById(R.id.viewHeatblastDrawerLayout);
        viewHeatblastActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), viewHeatblastDrawerLayout, R.string.nav_open, R.string.nav_close);
        viewHeatblastDrawerLayout.addDrawerListener(viewHeatblastActionBarDrawerToggle);
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.viewHeatblastNav);
        navigationView.setNavigationItemSelectedListener(this);
        viewHeatblastActionBarDrawerToggle.syncState();

        ItemTouchHelper helper1 = new ItemTouchHelper(callback1);
        helper1.attachToRecyclerView(viewHeatblastRecyclerView);


        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());
//
        // initializing our all variables.
        hList = new ArrayList<>();
//
        // getting our course array list from db handler class.
        hList = database.HreadAllHeatblast();

        // on below line passing our array list to our BAdapter class.
        BAdapter = new BAdapter(hList, getActivity());

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        viewHeatblastRecyclerView.setLayoutManager(linearLayoutManager);

        // setting our BAdapter to recycler view.
        viewHeatblastRecyclerView.setAdapter(BAdapter);
//
        viewHeatblastFab2.setVisibility(View.GONE);
        viewHeatblastFab3.setVisibility(View.GONE);
        viewHeatblasttv_todo.setVisibility(View.GONE);
        viewHeatblasttv_reminder.setVisibility(View.GONE);
        isAllFabsVisible = false;

        viewHeatblastFab1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            viewHeatblastFab2.show();
                            viewHeatblastFab3.show();
                            viewHeatblasttv_todo.setVisibility(View.VISIBLE);
                            viewHeatblasttv_reminder.setVisibility(View.VISIBLE);
                            isAllFabsVisible = true;
                        } else {
                            viewHeatblastFab2.hide();
                            viewHeatblastFab3.hide();
                            viewHeatblasttv_todo.setVisibility(View.GONE);
                            viewHeatblasttv_reminder.setVisibility(View.GONE);
                            isAllFabsVisible = false;
                        }
                    }
                });

        viewHeatblastFab2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), TodoFragment.class);
                        startActivity(i);
                    }
                });

        viewHeatblastFab3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity) requireActivity()).replaceFragments(ViewReminders.class);
                    }
                });


        return view;
    }

    ItemTouchHelper.SimpleCallback callback1 = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int frompos = viewHolder.getAdapterPosition();
            int topos = target.getAdapterPosition();

            Collections.swap(hList, frompos, topos);

            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(frompos, topos);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            switch (direction){
                case ItemTouchHelper.LEFT:
                    int position = viewHolder.getAdapterPosition();
                    BModel item = BAdapter.gethList().get(position);
                    BAdapter.removeItem(viewHolder.getAdapterPosition());

                    Snackbar snackbar = Snackbar.make(viewHeatblastDrawerLayout, "Item Deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    BAdapter.restoreItem(item, position);
                                    viewHeatblastRecyclerView.scrollToPosition(position);
                                }
                            }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                    super.onDismissed(transientBottomBar, event);

                                    if(!(event == DISMISS_EVENT_ACTION)){
                                        database.HdeleteSingleHeatblast(item.getId());
                                    }
                                }
                            });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                    break;

                case ItemTouchHelper.RIGHT:
                    int position1 = viewHolder.getAdapterPosition();
                    BModel item1 = BAdapter.gethList().get(position1);
                    BAdapter.removeItem(viewHolder.getAdapterPosition());

                    Snackbar snackbar1 = Snackbar.make(viewHeatblastDrawerLayout, "Item Deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    BAdapter.restoreItem(item1, position1);
                                    viewHeatblastRecyclerView.scrollToPosition(position1);
                                }
                            }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                    super.onDismissed(transientBottomBar, event);

                                    if(!(event == DISMISS_EVENT_ACTION)){
                                        database.HdeleteSingleHeatblast(item1.getId());
                                    }
                                }
                            });
                    snackbar1.setActionTextColor(Color.YELLOW);
                    snackbar1.show();
                    break;
            }
        }
    };

    //handling on back button pressed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //((MainActivity) requireActivity()).replaceFragments(BAddHeatblast.class);
                if(viewHeatblastDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    viewHeatblastDrawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    //((MainActivity) requireActivity()).replaceFragments(BAddHeatblast.class);
                    this.remove();
                    requireActivity().onBackPressed();
                }
            }
        });
    }

    //handling drawer navigation bar on item select events
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navtodo:
                Intent i = new Intent(getActivity(), TodoFragment.class);
                startActivity(i);
                break;
            case R.id.navarchive:
                ((MainActivity) requireActivity()).replaceFragments(TextPdf.class);
                break;
            case R.id.navshared:
                ((MainActivity) requireActivity()).replaceFragments(sticky_notes.class);
                break;
            case R.id.navduplicate:
                ((MainActivity) requireActivity()).replaceFragments(PaintFragment.class);
                break;
            case R.id.navdeleted:
                ((MainActivity) requireActivity()).replaceFragments(SplashFragment.class);
                break;
            case R.id.navsetting:
                ((MainActivity) requireActivity()).replaceFragments(SplashFragment.class);
                break;
            case R.id.nav_message:
                ((MainActivity) requireActivity()).replaceFragments(SplashFragment.class);
                break;
            case R.id.navbrave:
                ((MainActivity) requireActivity()).replaceFragments(SplashFragment.class);
                break;
            case R.id.nav_simple:
                ((MainActivity) requireActivity()).replaceFragments(PinnedFragment.class);
                break;
            case R.id.nav_heat:
                ((MainActivity) requireActivity()).replaceFragments(ViewHeatblast.class);
                break;
            case R.id.nav_Dairy:
                ((MainActivity) requireActivity()).replaceFragments(MemoriesFragment.class);
                break;
            case R.id.nav_reminder:
                ((MainActivity) requireActivity()).replaceFragments(ViewReminders.class);
                break;

        }
        viewHeatblastDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //inflating are menu file
        inflater.inflate(R.menu.options_menu1, menu);
        //below line toget our menu item
        MenuItem searchItem = menu.findItem(R.id.actionSearchHeatblast);
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

        //handling drawer navigation bar state after item was selected
        if (viewHeatblastActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if(id == R.id.actionDeleteAllHeatblast){
            database.HdeleteAllHeatblast();
            hList.clear();
            Toast.makeText(getActivity(), "All Notes deleted", Toast.LENGTH_SHORT).show();
            BAdapter.notifyDataSetChanged();
            viewHeatblastRecyclerView.setAdapter(BAdapter);
        }
        if(id == R.id.actionSearchHeatblast){
            Toast.makeText(getActivity(), "Search Notes", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void filter(String text){
        //creating a new arrafor filtering our data
        ArrayList<BModel> flist = new ArrayList<>();
        //running a for loop to match our data
        for(BModel item : hList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase()) || item.getDescription().toLowerCase().contains(text.toLowerCase())){
                flist.add(item);
            }
        }
        if (flist.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            BAdapter.HfilterList(flist);
        }
    }
}