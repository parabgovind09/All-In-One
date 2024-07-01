package com.example.allinone;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
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

public class UpdateHeatblast extends Fragment {

    View view;
    EditText edt_title, edt_description;
    Button btn_update, btn_noformat, btn_normal, btn_bold, btn_italic, btn_bolditalic, btn_underline, btn_leftalign,btn_centeralign, btn_rightalign, btn_strikrthrough;
    String titledata, descriptiondata, iddata;
    Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_heatblast2, container, false);

        //getting data from adapter class via main activity
        titledata = ((MainActivity) requireActivity()).getHtitledata();
        descriptiondata = ((MainActivity) requireActivity()).getHdescriptiondata();
        iddata = ((MainActivity) requireActivity()).getHiddata();

        edt_title = (EditText) view.findViewById(R.id.edt_title);
        edt_description = (EditText) view.findViewById(R.id.edt_description);
        btn_update = (Button) view.findViewById(R.id.btn_update);
        btn_noformat = (Button) view.findViewById(R.id.btn_noformat);
        btn_normal = (Button) view.findViewById(R.id.btn_normal);
        btn_bold = (Button) view.findViewById(R.id.btn_bold);
        btn_italic = (Button) view.findViewById(R.id.btn_italic);
        btn_bolditalic = (Button) view.findViewById(R.id.btn_bolditalic);
        btn_underline = (Button) view.findViewById(R.id.btn_underline);
        btn_leftalign = (Button) view.findViewById(R.id.btn_leftalign);
        btn_centeralign = (Button) view.findViewById(R.id.btn_centeralign);
        btn_rightalign = (Button) view.findViewById(R.id.btn_rightalign);
        btn_strikrthrough = (Button) view.findViewById(R.id.btn_strikethrough);


        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());

        edt_title.setText(titledata);
        edt_description.setText(descriptiondata);

        edt_title.setText(Html.fromHtml(titledata));
        edt_description.setText(Html.fromHtml(descriptiondata));

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // below we are getting our data from all edit text fields.
                String title = Html.toHtml(edt_title.getText()).trim();
                String description = Html.toHtml(edt_description.getText()).trim();

                // validating if the text fields are empty or not.
                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                //passing edittext values
                database.heatupdateHeatblast(title, description, iddata);
                Toast.makeText(getActivity(), "Data updated successfully....", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).replaceFragments(ViewHeatblast.class);
            }
        });

        btn_noformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    String noformattitletext = edt_title.getText().toString().trim();
                    edt_title.setText(noformattitletext);
                    edt_title.setSelection(titleEnd);
                    btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));

                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    String noformatdescriptiontext = edt_description.getText().toString().trim();
                    edt_description.setText(noformatdescriptiontext);
                    edt_description.setSelection(descriptionEnd);
                    btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                }
            }
        });

        btn_bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (titleStart < titleEnd){
                        Spannable spannableBold = new SpannableStringBuilder(edt_title.getText());
                        spannableBold.setSpan(new StyleSpan(Typeface.BOLD), titleStart, titleEnd, 0);
                        edt_title.setText(spannableBold);
                        edt_title.setSelection(titleEnd);
                        btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableBold = new SpannableStringBuilder(edt_description.getText());
                        spannableBold.setSpan(new StyleSpan(Typeface.BOLD), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableBold);
                        edt_description.setSelection(descriptionEnd);
                        btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                    }
                }
            }
        });

        btn_italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (titleStart < titleEnd){
                        Spannable spannableItalic = new SpannableStringBuilder(edt_title.getText());
                        spannableItalic.setSpan(new StyleSpan(Typeface.ITALIC), titleStart, titleEnd, 0);
                        edt_title.setText(spannableItalic);
                        edt_title.setSelection(titleEnd);
                        btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableItalic = new SpannableStringBuilder(edt_description.getText());
                        spannableItalic.setSpan(new StyleSpan(Typeface.ITALIC), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableItalic);
                        edt_description.setSelection(descriptionEnd);
                        btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                    }
                }
            }
        });

        btn_bolditalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (titleStart < titleEnd){
                        Spannable spannableBoldItalic = new SpannableStringBuilder(edt_title.getText());
                        spannableBoldItalic.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), titleStart, titleEnd, 0);
                        edt_title.setText(spannableBoldItalic);
                        edt_title.setSelection(titleEnd);
                        btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableBoldItalic = new SpannableStringBuilder(edt_description.getText());
                        spannableBoldItalic.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableBoldItalic);
                        edt_description.setSelection(descriptionEnd);
                        btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                    }
                }
            }
        });

        btn_underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying underline effect to it directly
                    if (titleStart < titleEnd){
                        Spannable spannableUnderline = new SpannableStringBuilder(edt_title.getText());
                        spannableUnderline.setSpan(new UnderlineSpan(), titleStart, titleEnd, 0);
                        edt_title.setText(spannableUnderline);
                        edt_title.setSelection(titleEnd);
                        btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying underline effect to it directly
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableUnderline = new SpannableStringBuilder(edt_description.getText());
                        spannableUnderline.setSpan(new UnderlineSpan(), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableUnderline);
                        edt_description.setSelection(descriptionEnd);
                        btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                    }
                }
            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (titleStart < titleEnd){
                        Spannable spannableNormal = new SpannableStringBuilder(edt_title.getText());
                        StyleSpan[] spans = spannableNormal.getSpans(titleStart, titleEnd, StyleSpan.class);
                        for (StyleSpan styleSpan : spans){
                            spannableNormal.removeSpan(styleSpan);
                        }
                        UnderlineSpan[] underlineSpans = spannableNormal.getSpans(titleStart, titleEnd, UnderlineSpan.class);
                        for (UnderlineSpan us : underlineSpans){
                            spannableNormal.removeSpan(us);
                        }
                        edt_title.setText(spannableNormal);
                        edt_title.setSelection(titleEnd);
                        btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_description.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableNormal = new SpannableStringBuilder(edt_description.getText());
                        StyleSpan[] spans = spannableNormal.getSpans(descriptionStart, descriptionEnd, StyleSpan.class);
                        for (StyleSpan styleSpan : spans){
                            spannableNormal.removeSpan(styleSpan);
                        }
                        UnderlineSpan[] underlineSpans = spannableNormal.getSpans(descriptionStart, descriptionEnd, UnderlineSpan.class);
                        for (UnderlineSpan us : underlineSpans){
                            spannableNormal.removeSpan(us);
                        }
                        edt_description.setText(spannableNormal);
                        edt_description.setSelection(descriptionEnd);
                        btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                    }
                }
            }
        });

        btn_leftalign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleEnd = edt_title.getSelectionEnd();
                    edt_title.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    Spannable spannableLeftAlign = new SpannableStringBuilder(edt_title.getText());
                    edt_title.setText(spannableLeftAlign);
                    edt_title.setSelection(titleEnd);
                    btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                }
                else{
                    int descriptionEnd = edt_description.getSelectionEnd();
                    edt_description.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    Spannable spannableLeftAlign = new SpannableStringBuilder(edt_description.getText());
                    edt_description.setText(spannableLeftAlign);
                    edt_description.setSelection(descriptionEnd);
                    btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                }
            }
        });

        btn_centeralign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleEnd = edt_title.getSelectionEnd();
                    edt_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Spannable spannableCenterAlign = new SpannableStringBuilder(edt_title.getText());
                    edt_title.setText(spannableCenterAlign);
                    edt_title.setSelection(titleEnd);
                    btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                }
                else{
                    int descriptionEnd = edt_description.getSelectionEnd();
                    edt_description.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Spannable spannableCenterAlign = new SpannableStringBuilder(edt_description.getText());
                    edt_description.setText(spannableCenterAlign);
                    edt_description.setSelection(descriptionEnd);
                    btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                }
            }
        });

        btn_rightalign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.LemonChiffon));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));

                if(edt_title.hasFocus()){
                    int titleEnd = edt_title.getSelectionEnd();
                    edt_title.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                    Spannable spannableRightAlign = new SpannableStringBuilder(edt_title.getText());
                    edt_title.setText(spannableRightAlign);
                    edt_title.setSelection(titleEnd);
                    btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                }
                else{
                    int descriptionEnd = edt_description.getSelectionEnd();
                    edt_description.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                    Spannable spannableRightAlign = new SpannableStringBuilder(edt_description.getText());
                    edt_description.setText(spannableRightAlign);
                    edt_description.setSelection(descriptionEnd);
                    btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                }
            }
        });

        btn_strikrthrough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));
                btn_normal.setBackgroundColor(btn_normal.getContext().getResources().getColor(R.color.white));
                btn_bold.setBackgroundColor(btn_bold.getContext().getResources().getColor(R.color.white));
                btn_italic.setBackgroundColor(btn_italic.getContext().getResources().getColor(R.color.white));
                btn_bolditalic.setBackgroundColor(btn_bolditalic.getContext().getResources().getColor(R.color.white));
                btn_underline.setBackgroundColor(btn_underline.getContext().getResources().getColor(R.color.white));
                btn_leftalign.setBackgroundColor(btn_leftalign.getContext().getResources().getColor(R.color.white));
                btn_centeralign.setBackgroundColor(btn_centeralign.getContext().getResources().getColor(R.color.white));
                btn_rightalign.setBackgroundColor(btn_rightalign.getContext().getResources().getColor(R.color.white));
                btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.LemonChiffon));

                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (titleStart < titleEnd){
                        Spannable spannableStrikeThrough = new SpannableStringBuilder(edt_title.getText());
                        spannableStrikeThrough.setSpan(new StrikethroughSpan(), titleStart, titleEnd, 0);
                        edt_title.setText(spannableStrikeThrough);
                        edt_title.setSelection(titleEnd);
                        btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    //if text is selected then applying bold effect to it directly
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableStrikeThrough = new SpannableStringBuilder(edt_description.getText());
                        spannableStrikeThrough.setSpan(new StrikethroughSpan(), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableStrikeThrough);
                        edt_description.setSelection(descriptionEnd);
                        btn_strikrthrough.setBackgroundColor(btn_strikrthrough.getContext().getResources().getColor(R.color.white));
                    }
                }
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
                ((MainActivity) requireActivity()).replaceFragments(ViewHeatblast.class);
            }
        });
    }
}