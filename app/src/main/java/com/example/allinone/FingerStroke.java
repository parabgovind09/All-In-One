package com.example.allinone;

import android.graphics.Path;

public class FingerStroke {

    // color of the stroke
    public int color;

    // width of the stroke
    public int strokeWidth;

    // a Path object to
    // represent the path drawn
    public Path path;

    //blur or not
    public boolean blur;

    //emboss or not
    public boolean emboss;

    // constructor to initialise the attributes
    public FingerStroke(int color, int strokeWidth, Path path, boolean blur, boolean emboss) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
        this.blur = blur;
        this.emboss = emboss;
    }
}


