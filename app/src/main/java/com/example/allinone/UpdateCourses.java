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

public class UpdateCourses extends Fragment {

    View view;
    private EditText courseNameEdt, courseDescriptionEdt;
    private Button updateCourseBtn, deleteCourseBtn;
    private Database dbHandler;
    String courseName, courseDescription,storedString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_courses, container, false);

        courseName = ((MainActivity) requireActivity()).getName();
        courseDescription = ((MainActivity) requireActivity()).getDescription();


        courseNameEdt = (EditText) view.findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = (EditText) view.findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = (Button) view.findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = (Button) view.findViewById(R.id.idBtnDeleteCourse);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new Database(getActivity());

        courseNameEdt.setText(courseName);
        courseDescriptionEdt.setText(courseDescription);

        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Html.toHtml(courseNameEdt.getText())
                dbHandler.updateCourse(courseName, courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString());
                Toast.makeText(getActivity(), "Note updated successfully....", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).replaceFragments(ViewCoursesFragment.class);
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteCourse(courseName);
                Toast.makeText(getActivity(), "Notes deleted successfully....", Toast.LENGTH_SHORT).show();
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