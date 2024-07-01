package com.example.allinone;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

public class AddHeatblast extends Fragment {

    View view;
    EditText edt_title, edt_description;
    Button btn_add, btn_view, btn_clearall, btn_noformat,btn_normal, btn_bold, btn_italic, btn_bolditalic, btn_underline,
            btn_leftalign,btn_centeralign, btn_rightalign, btn_strikrthrough, btn_texttospeech, btn_foregroundcolor,
            btn_backgroundcolor, btn_maskfilter, btn_colors;
    Database database;
    TextToSpeech textToSpeech;
    HorizontalScrollView horizontalScrollView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_heatblast2, container, false);

        // creating a new dbhandler class and passing our context to it.
        database = new Database(getActivity());

        horizontalScrollView1 = (HorizontalScrollView) view.findViewById(R.id.horizontalscrollview1);
        edt_title = (EditText) view.findViewById(R.id.edt_title);
        edt_description = (EditText) view.findViewById(R.id.edt_description);
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_view = (Button) view.findViewById(R.id.btn_view);
        btn_clearall = (Button) view.findViewById(R.id.btn_clearall);
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
        btn_texttospeech = (Button) view.findViewById(R.id.btn_texttospeech);
        btn_foregroundcolor = (Button) view.findViewById(R.id.btn_foregroundcolor);
        btn_backgroundcolor = (Button) view.findViewById(R.id.btn_backgroundcolor);
        btn_maskfilter = (Button) view.findViewById(R.id.btn_maskfilter);
        btn_colors = (Button) view.findViewById(R.id.btn_colors);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).replaceFragments(ViewHeatblast.class);
            }
        });

        btn_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireActivity());
                    alertDialog.setMessage("Are you sure ?");
                    alertDialog.setTitle("Heatblast Dialog Box");
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edt_title.setText("");
                        }
                    });
                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
                else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireActivity());
                    alertDialog.setMessage("Are you sure ?");
                    alertDialog.setTitle("Heatblast Dialog Box");
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edt_description.setText("");
                        }
                    });
                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog alertDialog1 = alertDialog.create();
                    alertDialog1.show();
                }
            }
        });

        btn_noformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    String noformattitletext = edt_title.getText().toString().trim();
                    edt_title.setText(noformattitletext);
                    edt_title.setSelection(edt_title.length());

                }
                else{
                    String noformatdescriptiontext = edt_description.getText().toString().trim();
                    edt_description.setText(noformatdescriptiontext);
                    edt_description.setSelection(edt_description.length());
                }
            }
        });

        btn_bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    if (titleStart > titleEnd){
                        int temp = titleEnd;
                        titleEnd = titleStart;
                        titleStart = temp;
                    }
                    if (titleStart < titleEnd){
                        Spannable spannableBold = new SpannableStringBuilder(edt_title.getText());
                        spannableBold.setSpan(new StyleSpan(Typeface.BOLD), titleStart, titleEnd, 0);
                        edt_title.setText(spannableBold);
                        edt_title.setSelection(titleEnd);
                    }

                    Spannable spannableBold = new SpannableStringBuilder(edt_title.getText());
                    spannableBold.setSpan(new StyleSpan(Typeface.BOLD), titleStart, titleEnd,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    edt_title.setText(spannableBold);
                    edt_title.setSelection(titleEnd);
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    if (descriptionStart > descriptionEnd){
                        int temp = descriptionEnd;
                        descriptionEnd = descriptionStart;
                        descriptionStart = temp;
                    }
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableBold = new SpannableStringBuilder(edt_description.getText());
                        spannableBold.setSpan(new StyleSpan(Typeface.BOLD), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableBold);
                        edt_description.setSelection(descriptionEnd);
                    }
                }
            }
        });

        btn_italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    if (titleStart > titleEnd){
                        int temp = titleEnd;
                        titleEnd = titleStart;
                        titleStart = temp;
                    }
                    if (titleStart < titleEnd){
                        Spannable spannableItalic = new SpannableStringBuilder(edt_title.getText());
                        spannableItalic.setSpan(new StyleSpan(Typeface.ITALIC), titleStart, titleEnd, 0);
                        edt_title.setText(spannableItalic);
                        edt_title.setSelection(titleEnd);
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    if (descriptionStart > descriptionEnd){
                        int temp = descriptionEnd;
                        descriptionEnd = descriptionStart;
                        descriptionStart = temp;
                    }
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableItalic = new SpannableStringBuilder(edt_description.getText());
                        spannableItalic.setSpan(new StyleSpan(Typeface.ITALIC), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableItalic);
                        edt_description.setSelection(descriptionEnd);
                    }
                }
            }
        });

        btn_bolditalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    if (titleStart > titleEnd){
                        int temp = titleEnd;
                        titleEnd = titleStart;
                        titleStart = temp;
                    }
                    if (titleStart < titleEnd){
                        Spannable spannableBoldItalic = new SpannableStringBuilder(edt_title.getText());
                        spannableBoldItalic.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), titleStart, titleEnd, 0);
                        edt_title.setText(spannableBoldItalic);
                        edt_title.setSelection(titleEnd);
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    if (descriptionStart > descriptionEnd){
                        int temp = descriptionEnd;
                        descriptionEnd = descriptionStart;
                        descriptionStart = temp;
                    }
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableBoldItalic = new SpannableStringBuilder(edt_description.getText());
                        spannableBoldItalic.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableBoldItalic);
                        edt_description.setSelection(descriptionEnd);
                    }
                }
            }
        });

        btn_underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    if (titleStart > titleEnd){
                        int temp = titleEnd;
                        titleEnd = titleStart;
                        titleStart = temp;
                    }
                    if (titleStart < titleEnd){
                        Spannable spannableUnderline = new SpannableStringBuilder(edt_title.getText());
                        spannableUnderline.setSpan(new UnderlineSpan(), titleStart, titleEnd, 0);
                        edt_title.setText(spannableUnderline);
                        edt_title.setSelection(titleEnd);
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    if (descriptionStart > descriptionEnd){
                        int temp = descriptionEnd;
                        descriptionEnd = descriptionStart;
                        descriptionStart = temp;
                    }
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableUnderline = new SpannableStringBuilder(edt_description.getText());
                        spannableUnderline.setSpan(new UnderlineSpan(), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableUnderline);
                        edt_description.setSelection(descriptionEnd);
                    }
                }
            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    int titleLength = edt_title.length();
                    if (titleStart > titleEnd){
                        int temp = titleEnd;
                        titleEnd = titleStart;
                        titleStart = temp;
                    }
                    if (titleStart < titleEnd){
                        if(titleStart == 0 && titleEnd == titleLength){
                            String noformattitletext = edt_title.getText().toString().trim();
                            edt_title.setText(noformattitletext);
                            edt_title.setSelection(titleEnd);
                        }
                        else if (titleStart != titleEnd && titleStart == 0){
                            Spannable spannableNormal = new SpannableStringBuilder(edt_title.getText());
                            Spannable spannableEnd = new SpannableStringBuilder(spannableNormal.subSequence(titleEnd + 0, titleLength + 0));
                            Spannable spannableNeglect = new SpannableStringBuilder(spannableNormal.subSequence(titleStart + 0, titleEnd + 0));
                            String text = spannableNeglect.toString();
                            Spannable spannableFinal = new SpannableStringBuilder(TextUtils.concat(text,spannableEnd));
                            edt_title.setText(spannableFinal);
                            edt_title.setSelection(titleEnd);
                        }
                        else{
                            Spannable spannableText = new SpannableStringBuilder(edt_title.getText());
                            int ts = edt_title.getSelectionStart();
                            int te = edt_title.getSelectionEnd();
                            ClickableSpan clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                }
                                @Override
                                public void updateDrawState(@NonNull TextPaint ds) {
                                    ds.setUnderlineText(false);
                                    ds.setStrikeThruText(false);
                                }
                            };
                            spannableText.setSpan(clickableSpan,ts,te,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                            StyleSpan[] styleSpans;
                            styleSpans = spannableText.getSpans(ts,te,StyleSpan.class);
                            for (int i = 0; i < styleSpans.length; i++){
                                StyleSpan styleSpan = styleSpans[i];
                                StyleSpan styleSpan1 = styleSpans[i];
                                int ss = spannableText.getSpanStart(styleSpan);
                                int se = spannableText.getSpanEnd(styleSpan);
                                spannableText.removeSpan(styleSpan);
                                if (ts!=ss || te!=se){
                                    if(ts-1 > ss && ts-1 >=0 && styleSpan1.getStyle() == Typeface.BOLD){
                                        spannableText.setSpan(new StyleSpan(Typeface.BOLD), ss, ts, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                                    }
                                    if (se>te+1 && te+1 <spannableText.length() && styleSpan1.getStyle() == Typeface.BOLD){
                                        spannableText.setSpan(new StyleSpan(Typeface.BOLD), te, se, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                                    }
                                    if(ts-1 > ss && ts-1 >=0 && styleSpan1.getStyle() == Typeface.ITALIC){
                                        spannableText.setSpan(new StyleSpan(Typeface.ITALIC), ss, ts, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                                    }
                                    if (se>te+1 && te+1 <spannableText.length() && styleSpan1.getStyle() == Typeface.ITALIC){
                                        spannableText.setSpan(new StyleSpan(Typeface.ITALIC), te, se, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                                    }
                                    if(ts-1 > ss && ts-1 >=0 && styleSpan1.getStyle() == Typeface.BOLD_ITALIC){
                                        spannableText.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), ss, ts, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                                    }
                                    if (se>te+1 && te+1 <spannableText.length() && styleSpan1.getStyle() == Typeface.BOLD_ITALIC){
                                        spannableText.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), te, se, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                                    }
                                }
                            }
                            edt_title.setText(spannableText);
                            edt_title.setSelection(te);
                        }
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_description.getSelectionEnd();
                    if (descriptionStart > descriptionEnd){
                        int temp = descriptionEnd;
                        descriptionEnd = descriptionStart;
                        descriptionStart = temp;
                    }
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
                    }
                }
            }
        });

        btn_leftalign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleEnd = edt_title.getSelectionEnd();
                    edt_title.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    Spannable spannableLeftAlign = new SpannableStringBuilder(edt_title.getText());
                    edt_title.setText(spannableLeftAlign);
                    edt_title.setSelection(titleEnd);
                }
                else{
                    int descriptionEnd = edt_description.getSelectionEnd();
                    edt_description.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    Spannable spannableLeftAlign = new SpannableStringBuilder(edt_description.getText());
                    edt_description.setText(spannableLeftAlign);
                    edt_description.setSelection(descriptionEnd);
                }
            }
        });

        btn_centeralign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleEnd = edt_title.getSelectionEnd();
                    edt_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Spannable spannableCenterAlign = new SpannableStringBuilder(edt_title.getText());
                    edt_title.setText(spannableCenterAlign);
                    edt_title.setSelection(titleEnd);
                }
                else{
                    int descriptionEnd = edt_description.getSelectionEnd();
                    edt_description.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    Spannable spannableCenterAlign = new SpannableStringBuilder(edt_description.getText());
                    edt_description.setText(spannableCenterAlign);
                    edt_description.setSelection(descriptionEnd);
                }
            }
        });

        btn_rightalign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleEnd = edt_title.getSelectionEnd();
                    edt_title.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                    Spannable spannableRightAlign = new SpannableStringBuilder(edt_title.getText());
                    edt_title.setText(spannableRightAlign);
                    edt_title.setSelection(titleEnd);
                }
                else{
                    int descriptionEnd = edt_description.getSelectionEnd();
                    edt_description.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                    Spannable spannableRightAlign = new SpannableStringBuilder(edt_description.getText());
                    edt_description.setText(spannableRightAlign);
                    edt_description.setSelection(descriptionEnd);
                }
            }
        });

        btn_strikrthrough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_title.hasFocus()){
                    int titleStart = edt_title.getSelectionStart();
                    int titleEnd = edt_title.getSelectionEnd();
                    if (titleStart > titleEnd){
                        int temp = titleEnd;
                        titleEnd = titleStart;
                        titleStart = temp;
                    }
                    if (titleStart < titleEnd){
                        Spannable spannableStrikeThrough = new SpannableStringBuilder(edt_title.getText());
                        spannableStrikeThrough.setSpan(new StrikethroughSpan(), titleStart, titleEnd, 0);
                        edt_title.setText(spannableStrikeThrough);
                        edt_title.setSelection(titleEnd);
                    }
                }
                else{
                    int descriptionStart = edt_description.getSelectionStart();
                    int descriptionEnd = edt_title.getSelectionEnd();
                    if (descriptionStart > descriptionEnd){
                        int temp = descriptionEnd;
                        descriptionEnd = descriptionStart;
                        descriptionStart = temp;
                    }
                    if (descriptionStart < descriptionEnd){
                        Spannable spannableStrikeThrough = new SpannableStringBuilder(edt_description.getText());
                        spannableStrikeThrough.setSpan(new StrikethroughSpan(), descriptionStart, descriptionEnd, 0);
                        edt_description.setText(spannableStrikeThrough);
                        edt_description.setSelection(descriptionEnd);
                    }
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Html.toHtml(edt_title.getText()).trim();
                String description = Html.toHtml(edt_description.getText()).trim();
                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                database.heataddNewHeatblast(title, description);
                Toast.makeText(getActivity(), "Heatblast added..", Toast.LENGTH_SHORT).show();
                edt_title.setText("");
                edt_description.setText("");
            }
        });

        btn_texttospeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_title.hasFocus()){
                    String text = edt_title.getText().toString();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null,null);
                    }
                    else{
                        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                else{
                    String text = edt_description.getText().toString();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null,null);
                    }
                    else{
                        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        btn_colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet2,null);
                LinearLayout linearLayout1 = view.findViewById(R.id.linearlayout1);
                TextView textview0 = (TextView) view.findViewById(R.id.textview0);
                SeekBar seekBar0 = (SeekBar) view.findViewById(R.id.seekbar0);
                HorizontalScrollView horizontalScrollView1 = view.findViewById(R.id.horizontalscrollview1);
                LinearLayout linearLayout2 = view.findViewById(R.id.linearlayout2);
                seekBar0.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        textview0.setText("Size"+i);
                        edt_title.setTextSize(i);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

                int [] colorsarray = requireContext().getResources().getIntArray(R.array.colorbox);
                for (int i = 0; i <= 150; i++){
                    final Button button = new Button(getActivity());
                    button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    button.setId(i);
                    button.setBackgroundColor(colorsarray[i]);
                    button.setText(String.valueOf(i));
                    final int finalI = i;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getActivity(), "b "+finalI, Toast.LENGTH_SHORT).show();
                        }
                    });
                    linearLayout2.addView(button);
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
                ((MainActivity) requireActivity()).replaceFragments(ViewHeatblast.class);
            }
        });
    }
}
//btn_noformat.setBackgroundColor(btn_noformat.getContext().getResources().getColor(R.color.white));