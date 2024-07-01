package com.example.allinone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class BottomSheetFragment2 extends BottomSheetDialogFragment {

    View view;
    HorizontalScrollView horizontalScrollView1;
    TextView textview0, textView1;
    SeekBar seekBar0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_sheet2, container, false);
        horizontalScrollView1 = (HorizontalScrollView) view.findViewById(R.id.horizontalscrollview1);
        textview0 = (TextView) view.findViewById(R.id.textview0);
        seekBar0 = (SeekBar) view.findViewById(R.id.seekbar0);
        int [] colorsarray = requireContext().getResources().getIntArray(R.array.colorbox);
        for (int i = 0; i < 150; i++){
            //creating buttons in a loop
            final Button button = new Button(getActivity());
            button.setLayoutParams(new HorizontalScrollView.LayoutParams(HorizontalScrollView.LayoutParams.MATCH_PARENT,HorizontalScrollView.LayoutParams.WRAP_CONTENT));
            button.setId(i);
            button.setBackgroundColor(colorsarray[i]);
            button.setText(i);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "b "+finalI, Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            });
            horizontalScrollView1.addView(button);
        }
        return view;
    }
}