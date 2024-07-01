package com.example.allinone;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class sticky_notes extends Fragment {

    private EditText mEditText;
//    private Button decrement,increment,bold,underline,itallic;
//    private TextView sizeindicator;
    private FloatingActionButton fab;
    StickyNote note = new StickyNote();
    float currentsize;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sticky_notes, container, false);
        // getting the reference of the EditText
        mEditText = (EditText) view.findViewById(R.id.editText);

//        Button decrement = view.findViewById(R.id.decrement);
//        Button increment = view.findViewById(R.id.increment);
//        Button bold = view.findViewById(R.id.bold);
//        Button underline = view.findViewById(R.id.underline);
//        Button itallic = view.findViewById(R.id.itallic);
//        TextView sizeindicator = view.findViewById(R.id.sizeindicator);
        FloatingActionButton fab = view.findViewById(R.id.save);
        currentsize = mEditText.getTextSize();
//        sizeindicator.setText(""+currentsize);

//        increment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sizeindicator.setText(""+currentsize);
//                currentsize++;
//                mEditText.setTextSize(currentsize);
//            }
//        });
//        decrement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sizeindicator.setText(""+currentsize);
//                currentsize--;
//                mEditText.setTextSize(currentsize);
//            }
//        });
//        itallic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mEditText.getTypeface().isItalic()){
//                    mEditText.setTypeface(Typeface.DEFAULT);
//                }
//                else {
//                    mEditText.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
//                }
//            }
//        });
//        underline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mEditText.getPaintFlags()==8){
//                    mEditText.setPaintFlags(mEditText.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
//                }
//                else {
//                    mEditText.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//                }
//            }
//        });
//        bold.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mEditText.getTypeface().isBold()){
//                    mEditText.setTypeface(Typeface.DEFAULT);
//                }
//                else {
//                    mEditText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                }
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setStick(mEditText.getText().toString().trim(),requireContext());
                updateWidget();
            }
        });

        return view;
    }

    // function to update the
    // Widget(Sticky Note) every time
    // user saves the note
    public void updateWidget() {
        note.setStick(mEditText.getText().toString(), requireContext());
        // the AppWidgetManager helps
        // us to manage all the
        // widgets from this app
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());

        // the RemoteViews class allows us to inflate a
        // layout resource file hierarchy and provides some
        // basic operations for modifying the content of the
        // inflated hierarchy
        RemoteViews remoteViews = new RemoteViews(requireContext().getPackageName(), R.layout.new_app_widget);

        // by using ComponentName class we can get specific
        // application component and to identify the
        // component we pass the package name and the class
        // name inside of that package
        ComponentName thisWidget = new ComponentName(getContext(), AppWidget.class);

        // update the text of the textview of the widget
        remoteViews.setTextViewText(R.id.appwidget_text, mEditText.getText().toString());

        // finally us the AppWidgetManager instance to
        // update all the widgets
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        Toast.makeText(getContext(), "Updated Successfully!!", Toast.LENGTH_SHORT).show();
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