package com.example.allinone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MemoriesFragment extends Fragment {

    View view;
    GridView gridView;
    FloatingActionButton myfab;
    Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enabling options menu in this fragment
        setHasOptionsMenu(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_memories, container, false);
        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());
        gridView = (GridView) view.findViewById(R.id.activity_main_grid_view);
        gridView.setAdapter(new MemoriesAdapter(getActivity(), this.database.readAllMemories(), false));
        gridView.setEmptyView(view.findViewById(R.id.activity_main_empty_view));
        myfab = (FloatingActionButton) view.findViewById(R.id.myFAB);

        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).replaceFragments(NewMemoryFragment.class);
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((CursorAdapter)gridView.getAdapter()).swapCursor(this.database.readAllMemories());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //inflating are menu file
        inflater.inflate(R.menu.memory_options_menu, menu);
        //below line toget our menu item
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.actionDeleteMemory:
                database.deleteAllMemory();
                Toast.makeText(getActivity(), "All Diary Notes deleted", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                ((MainActivity) requireActivity()).replaceFragments(BViewHeatblast.class);
                break;
        }

        return true;
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