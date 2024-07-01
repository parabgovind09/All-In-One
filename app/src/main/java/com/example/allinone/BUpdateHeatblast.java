package com.example.allinone;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BUpdateHeatblast extends Fragment {

    View view;
    EditText updateHeatblastedt_title, updateHeatblastedt_description;
    TextView updateHeatblasttv_update, updateHeatblasttv_updatedatetime;
    ImageButton updateHeatblastImageButton;
    String updateIdData, updateTitleData, updateDescriptionData, updateCurrentDateTimeData, updatedatetime;
    Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_heatblast, container, false);
        //getting data from BAdapter class via main activity
        updateTitleData = ((MainActivity) requireActivity()).getTitleData();
        updateDescriptionData = ((MainActivity) requireActivity()).getDescriptionData();
        updateIdData = ((MainActivity) requireActivity()).getIdData();
        updatedatetime = ((MainActivity) requireActivity()).getUpdatedatetime();

        updateHeatblastedt_title = (EditText) view.findViewById(R.id.updateHeatblastedt_title);
        updateHeatblastedt_description = (EditText) view.findViewById(R.id.updateHeatblastedt_description);
        updateHeatblasttv_updatedatetime = (TextView) view.findViewById(R.id.updateHeatblasttv_updatedatetime);

        updateHeatblastImageButton = (ImageButton) view.findViewById(R.id.updateHeatblastImageButton);
        updateHeatblasttv_update = (TextView) view.findViewById(R.id.updateHeatblasttv_update);

        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());

        //setting the data to our views
        updateHeatblastedt_title.setText(updateTitleData);
        updateHeatblastedt_description.setText(updateDescriptionData);
        updateHeatblasttv_updatedatetime.setText(updatedatetime);


        updateHeatblasttv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // below we are getting our data from all edit text fields.
                String title = updateHeatblastedt_title.getText().toString().trim();
                String description = updateHeatblastedt_description.getText().toString().trim();

                // validating if the text fields are empty or not.
                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                //passing edittext values
                database.HupdateHeatblast(title, description, updateIdData);
                Toast.makeText(getActivity(), "Heatblast updated successfully....", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).replaceFragments(BViewHeatblast.class);
            }
        });

        updateHeatblastImageButton.setOnClickListener(new View.OnClickListener() {
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
                ((MainActivity) requireActivity()).replaceFragments(BViewHeatblast.class);
            }
        });
    }
}