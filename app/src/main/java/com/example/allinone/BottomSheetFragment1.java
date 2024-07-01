package com.example.allinone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment1 extends BottomSheetDialogFragment {

    View view;
    TextView bottomSheetFragment1tv_viewall1,bottomSheetFragment1tv_viewall2;
    HorizontalScrollView bottomSheetFragment1HorizontalScrollview1,bottomSheetFragment1HorizontalScrollview2;
    LinearLayout bottomSheetFragment1LinearLayout1,bottomSheetFragment1LinearLayout2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bottom_sheet1, container, false);
        bottomSheetFragment1tv_viewall1 = (TextView) view.findViewById(R.id.bottomSheetFragment1tv_viewall1);
        bottomSheetFragment1HorizontalScrollview1 = (HorizontalScrollView) view.findViewById(R.id.bottomSheetFragment1HorizontalScrollview1);
        bottomSheetFragment1LinearLayout1 = (LinearLayout) view.findViewById(R.id.bottomSheetFragment1LinearLayout1);
        bottomSheetFragment1tv_viewall2 = (TextView) view.findViewById(R.id.bottomSheetFragment1tv_viewall2);
        bottomSheetFragment1HorizontalScrollview2 = (HorizontalScrollView) view.findViewById(R.id.bottomSheetFragment1HorizontalScrollview2);
        bottomSheetFragment1LinearLayout2 = (LinearLayout) view.findViewById(R.id.bottomSheetFragment1LinearLayout2);
        return view;
    }
}