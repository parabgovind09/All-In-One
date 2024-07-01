package com.example.allinone;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextPdf extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    int xx=10,yy=40;
    Paint paint;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_pdf, container, false);
        EditText inputtext = view.findViewById(R.id.inputtext);
        ProgressBar progressbar = view.findViewById(R.id.progressbar);
        Button create = view.findViewById(R.id.create);
        Button lefttext = view.findViewById(R.id.lefttext);
        Button centertext = view.findViewById(R.id.centertext);
        Button righttext = view.findViewById(R.id.righttext);
        Button bold = view.findViewById(R.id.bold);
        Button itallic = view.findViewById(R.id.itallic);
        Button underline = view.findViewById(R.id.underline);

        //paint work
        paint = new Paint();
        //paint.setColor(Color.BLUE);

        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
        });
        itallic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setUnderlineText(true);
            }
        });
        lefttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xx=10;
                yy=40;
                paint.setTextAlign(Paint.Align.LEFT);
            }
        });
        centertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xx=280;
                yy=40;
                paint.setTextAlign(Paint.Align.CENTER);
            }
        });
        righttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xx=550;
                yy=40;
                paint.setTextAlign(Paint.Align.RIGHT);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                //create a new document
                PdfDocument document = new PdfDocument();

                //putting the left corner start point in x and y
                int x = xx, y = yy;

                //taking user string
                String input = inputtext.getText().toString().trim();

                //create a page description
                int page_width = 560;
                int page_height = 900;
                int page_number = 1;
                long page_line = 1L;
                long counter = 60L;
                PdfDocument.PageInfo pageInfo;
                PdfDocument.Page page;

                pageInfo = new PdfDocument.PageInfo.Builder(page_width, page_height, page_number).create();

                //start a page
                page = document.startPage(pageInfo);

                String part;
                //(?!.\s)|(?!\s)
                String pattern = ".{0,80}(?!\\S)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(input);
                while(m.find()){
                    part = Objects.requireNonNull(m.group(0)).trim();
                    for (String linesep : part.split("\n")) {
                        page.getCanvas().drawText(linesep, x, y, paint);
                        y += paint.descent() - paint.ascent();
                        page_line++;
                        if(page_line>=counter){
                            document.finishPage(page);
                            page_number++;
                            counter+=60;
                            //putting the left corner start point in x and y
                            x = xx;
                            y = yy;
                            pageInfo = new PdfDocument.PageInfo.Builder(page_width, page_height, page_number).create();

                            //start a page
                            page = document.startPage(pageInfo);
                        }
                    }
                }


                //finish the page
                document.finishPage(page);

                String myFilePath = Environment.getExternalStorageDirectory().getPath()+"/myPDFFile.pdf";
                File file = new File(myFilePath);
                try {
                    document.writeTo(new FileOutputStream(file));
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                progressbar.setVisibility(View.GONE);

                //close the page
                document.close();

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