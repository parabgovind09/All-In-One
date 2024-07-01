package com.example.allinone;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class BAddHeatblast extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    View view;
    public DrawerLayout addHeatblastDrawerLayout;
    public ActionBarDrawerToggle addHeatblastActionBarDrawerToggle;
    EditText addHeatblastedt_title, addHeatblastedt_description;
    ImageButton addHeatblastImageButton;
    TextView addHeatblasttv_save;
    Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_heatblast, container, false);
        addHeatblastedt_title = (EditText) view.findViewById(R.id.addHeatblastedt_title);
        addHeatblastedt_description = (EditText) view.findViewById(R.id.addHeatblastedt_description);
        addHeatblasttv_save = (TextView) view.findViewById(R.id.addHeatblasttv_save);
        addHeatblastDrawerLayout = (DrawerLayout) view.findViewById(R.id.addHeatblastDrawerLayout);
        addHeatblastImageButton = (ImageButton) view.findViewById(R.id.addHeatblastImageButton);
        addHeatblastActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), addHeatblastDrawerLayout, R.string.nav_open, R.string.nav_close);
        addHeatblastDrawerLayout.addDrawerListener(addHeatblastActionBarDrawerToggle);
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.addHeatblastNav);
        navigationView.setNavigationItemSelectedListener(this);
        addHeatblastActionBarDrawerToggle.syncState();

        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());

        addHeatblasttv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = addHeatblastedt_title.getText().toString().trim();
                String description = addHeatblastedt_description.getText().toString().trim();
                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                database.HaddNewHeatblast(title, description);
                Toast.makeText(getActivity(), "Heatblast added..", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).replaceFragments(BViewHeatblast.class);
            }
        });

        addHeatblastImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet1,null);
                TextView bottomSheetFragment1tv_viewall1 = (TextView) view.findViewById(R.id.bottomSheetFragment1tv_viewall1);
                HorizontalScrollView bottomSheetFragment1HorizontalScrollview1 = view.findViewById(R.id.bottomSheetFragment1HorizontalScrollview1);
                LinearLayout bottomSheetFragment1LinearLayout1 = view.findViewById(R.id.bottomSheetFragment1LinearLayout1);
                TextView bottomSheetFragment1tv_viewall2 = (TextView) view.findViewById(R.id.bottomSheetFragment1tv_viewall2);
                HorizontalScrollView bottomSheetFragment1HorizontalScrollview2 = view.findViewById(R.id.bottomSheetFragment1HorizontalScrollview2);
                LinearLayout bottomSheetFragment1LinearLayout2 = view.findViewById(R.id.bottomSheetFragment1LinearLayout2);
                int [] colorsarray = requireContext().getResources().getIntArray(R.array.colorbox);
                int [] gradientcolorsarray1 = requireContext().getResources().getIntArray(R.array.gradientcolorbox1);
                int [] gradientcolorsarray2 = requireContext().getResources().getIntArray(R.array.gradientcolorbox2);
                for (int i = 0; i <= 150; i++){
                    final Button button = new Button(requireActivity());
                    button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    button.setId(i);
                    button.setBackgroundColor(colorsarray[i]);
                    button.setText(String.valueOf(i));
                    final int finalI = i;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getActivity(), "b "+finalI, Toast.LENGTH_SHORT).show();
                            //edt_title.setBackgroundColor(colorsarray[finalI]);
                        }
                    });
                    bottomSheetFragment1LinearLayout1.addView(button);
                }
                for (int i = 0; i <= 128; i++){
                    int[] gradientcolor = {gradientcolorsarray1[i],gradientcolorsarray2[i]};
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,gradientcolor);
                    gradientDrawable.setCornerRadius(0f);
                    final Button button = new Button(getActivity());
                    button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    button.setId(i);
                    button.setBackground(gradientDrawable);
                    button.setText(String.valueOf(i));
                    final int finalI = i;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getActivity(), "b "+finalI, Toast.LENGTH_SHORT).show();
                            //edt_title.setBackground(gradientDrawable);
                        }
                    });
                    bottomSheetFragment1LinearLayout2.addView(button);
                }
                BottomSheetDialog dialog = new BottomSheetDialog(requireActivity());
                dialog.setContentView(view);
                dialog.show();
            }
        });

        return view;
    }

    //handling on back button pressed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(addHeatblastDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    addHeatblastDrawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    ((MainActivity) requireActivity()).replaceFragments(BViewHeatblast.class);
                }
            }
        });
    }

    //handling drawer navigation bar on item select events
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navtodo:
                Toast.makeText(getContext(), "You selected my account1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navarchive:
                Toast.makeText(getContext(), "You selected my account1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navshared:
                Toast.makeText(getContext(), "You selected todo1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navduplicate:
                Toast.makeText(getContext(), "You selected my share1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navdeleted:
                Toast.makeText(getContext(), "You selected my message1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navsetting:
                Toast.makeText(getContext(), "You selected my message1", Toast.LENGTH_SHORT).show();
                break;
        }
        addHeatblastDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //handling drawer navigation bar state after item was selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (addHeatblastActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}