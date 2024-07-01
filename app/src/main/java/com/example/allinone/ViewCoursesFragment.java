package com.example.allinone;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ViewCoursesFragment extends Fragment {

    private ArrayList<CourseModal> courseModalArrayList;
    private Database dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    private CourseModal modal;
    private CoordinatorLayout coordinatorLayout;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_courses, container, false);

        coordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinatelayout);
        coursesRV = (RecyclerView)view.findViewById(R.id.idRVCourses);

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(coursesRV);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new Database(getActivity());

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our RAdapter class.
        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, getActivity());
        coursesRV = (RecyclerView) view.findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our RAdapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);

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
            CourseModal item = courseRVAdapter.getCourseModalArrayList().get(position);
            courseRVAdapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Item Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            courseRVAdapter.restoreItem(item, position);
                            coursesRV.scrollToPosition(position);
                        }
                    }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);

                            if(!(event == DISMISS_EVENT_ACTION)){
                                dbHandler.deleteCourse(item.getCourseName());
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
        inflater.inflate(R.menu.normalnotes_menu, menu);
        //below line toget our menu item
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        //getting searchview of our item
        SearchView searchView = (SearchView) searchItem.getActionView();
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


    private void filter(String text){
        //creating a new arrafor filtering our data
        ArrayList<CourseModal> filteredlist = new ArrayList<>();
        //running a for loop to match our data
        for(CourseModal item : courseModalArrayList){
            if(item.getCourseName().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            courseRVAdapter.filterList(filteredlist);
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