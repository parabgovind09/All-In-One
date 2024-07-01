package com.example.allinone;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.RangeSlider;

import java.util.UUID;

public class PaintFragment extends Fragment {
    // creating the object of type DrawView
    // in order to get the reference of the View
    private DrawPaint paint;
    // creating objects of type button
    private ImageButton save, color, stroke, undo,clear, emboss, blur, normal;

    // creating a RangeSlider object, which will
    // help in selecting the width of the Stroke
    private RangeSlider rangeSlider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paint, container, false);
        // getting the reference of the views from their ids
        paint = (DrawPaint) view.findViewById(R.id.draw_view);
        rangeSlider = (RangeSlider) view.findViewById(R.id.rangebar);
        undo = (ImageButton) view.findViewById(R.id.btn_undo);
        save = (ImageButton) view.findViewById(R.id.btn_save);
        color = (ImageButton) view.findViewById(R.id.btn_color);
        stroke = (ImageButton) view.findViewById(R.id.btn_stroke);
        clear = (ImageButton) view.findViewById(R.id.btn_clear);
        emboss = (ImageButton) view.findViewById(R.id.btn_emboss);
        blur = (ImageButton) view.findViewById(R.id.btn_blur);
        normal = (ImageButton) view.findViewById(R.id.btn_normal);

        // creating a OnClickListener for each button,
        // to perform certain actions

        // the undo button will remove the most
        // recent stroke from the canvas
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.undo();
            }
        });

        // the save button will save the current
        // canvas which is actually a bitmap
        // in form of PNG, in the storage
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setDrawingCacheEnabled(true);
                String imgsaver = MediaStore.Images.Media.insertImage(requireContext().getContentResolver(),paint.getDrawingCache(), UUID.randomUUID().toString()+".png","drawing");
                paint.destroyDrawingCache();
            }
        });
        // the color button will allow the user
        // to select the color of his brush
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.setColor(Color.GREEN);
            }
        });
        blur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.blur();
            }
        });
        emboss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.emboss();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.clear();
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.normal();
            }
        });
        // the button will toggle the visibility of the RangeBar/RangeSlider
        stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rangeSlider.getVisibility() == View.VISIBLE)
                    rangeSlider.setVisibility(View.GONE);
                else
                    rangeSlider.setVisibility(View.VISIBLE);
            }
        });

        // set the range of the RangeSlider
        rangeSlider.setValueFrom(0.0f);
        rangeSlider.setValueTo(100.0f);

        // adding a OnChangeListener which will
        // change the stroke width
        // as soon as the user slides the slider
        rangeSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                paint.setStrokeWidth((int) value);
            }
        });

        // pass the height and width of the custom view
        // to the init method of the DrawView object
        ViewTreeObserver vto = paint.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                paint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = paint.getMeasuredWidth();
                int height = paint.getMeasuredHeight();
                paint.init(height, width);
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