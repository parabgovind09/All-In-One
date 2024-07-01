package com.example.allinone;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PinnedFragment extends Fragment {

    View view;
    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt, courseDescriptionEdt;
    private Button addCourseBtn, readCourseBtn;
    private Database dbHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pinned, container, false);

        // initializing all our variables.
        courseNameEdt = (EditText) view.findViewById(R.id.idEdtCourseName);

        courseDescriptionEdt = (EditText) view.findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = (Button) view.findViewById(R.id.idBtnAddCourse);
        readCourseBtn = (Button) view.findViewById(R.id.idBtnReadCourse);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new Database(getActivity());

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseDescription);

                // after adding the data we are displaying a toast message.
                Toast.makeText(getActivity(), "Notes has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).replaceFragments(ViewCoursesFragment.class);
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
                ((MainActivity) requireActivity()).replaceFragments(ViewCoursesFragment.class);
            }
        });
    }
}