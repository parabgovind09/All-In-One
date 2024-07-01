package com.example.allinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

//SHA1: 19:59:8F:3F:4F:30:B4:E8:33:F1:F0:8A:19:FB:D5:B9:2D:D4:CD:26
//SHA-256: EB:4D:05:00:A7:53:5F:14:3D:5D:9A:99:2A:34:C4:FC:75:F1:53:F5:29:7C:6C:8D:89:10:8D:02:96:CC:10:30

public class MainActivity extends AppCompatActivity {

    String idData, titleData, descriptionData, currentDateTimeData, updatedatetime;

    /**********************************************/
    String titledata,descriptiondata,imageiddata;
    /**********************************************/

    String clocktitledata, clockdescriptiondata, clockdatedata, clocktimedata, clockiddata;
    int clockuniddata;

    /********************************************************/
    String htitledata, hdescriptiondata, hiddata;
    /*********************************************/


    String name, description, duration, tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                    .add(R.id.fragment_container, HomeFragment.class, null).commit();
        }
//
//        Intent i = new Intent(MainActivity.this,TodoFragment.class);
//        startActivity(i);
    }

    //method to replace fragment
    public void replaceFragments(Class fragmentClass){
        Fragment fragment = null;
        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        //Insert the fragment by replacing any exixting fragment
        assert fragment != null;
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                replace(R.id.fragment_container, fragment).commit();
    }

    public String getTitledata() {
        return titledata;
    }

    public void setTitledata(String titledata) {
        this.titledata = titledata;
    }

    public String getDescriptiondata() {
        return descriptiondata;
    }

    public void setDescriptiondata(String descriptiondata) {
        this.descriptiondata = descriptiondata;
    }

    public String getImageiddata() {
        return imageiddata;
    }

    public void setImageiddata(String imageiddata) {
        this.imageiddata = imageiddata;
    }

    public String getClocktitledata() {
        return clocktitledata;
    }

    public void setClocktitledata(String clocktitledata) {
        this.clocktitledata = clocktitledata;
    }

    public String getClockdescriptiondata() {
        return clockdescriptiondata;
    }

    public void setClockdescriptiondata(String clockdescriptiondata) {
        this.clockdescriptiondata = clockdescriptiondata;
    }

    public String getClockdatedata() {
        return clockdatedata;
    }

    public void setClockdatedata(String clockdatedata) {
        this.clockdatedata = clockdatedata;
    }

    public String getClocktimedata() {
        return clocktimedata;
    }

    public void setClocktimedata(String clocktimedata) {
        this.clocktimedata = clocktimedata;
    }

    public String getClockiddata() {
        return clockiddata;
    }

    public void setClockiddata(String clockiddata) {
        this.clockiddata = clockiddata;
    }

    public int getClockuniddata() {
        return clockuniddata;
    }

    public void setClockuniddata(int clockuniddata) {
        this.clockuniddata = clockuniddata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTracks() {
        return tracks;
    }

    public void setTracks(String tracks) {
        this.tracks = tracks;
    }

    public String getIdData() {
        return idData;
    }

    public void setIdData(String idData) {
        this.idData = idData;
    }

    public String getTitleData() {
        return titleData;
    }

    public void setTitleData(String titleData) {
        this.titleData = titleData;
    }

    public String getDescriptionData() {
        return descriptionData;
    }

    public void setDescriptionData(String descriptionData) {
        this.descriptionData = descriptionData;
    }

    public String getCurrentDateTimeData() {
        return currentDateTimeData;
    }

    public void setCurrentDateTimeData(String currentDateTimeData) {
        this.currentDateTimeData = currentDateTimeData;
    }

    public String getUpdatedatetime() {
        return updatedatetime;
    }

    public void setUpdatedatetime(String updatedatetime) {
        this.updatedatetime = updatedatetime;
    }

    public String getHtitledata() {
        return htitledata;
    }

    public void setHtitledata(String htitledata) {
        this.htitledata = htitledata;
    }

    public String getHdescriptiondata() {
        return hdescriptiondata;
    }

    public void setHdescriptiondata(String hdescriptiondata) {
        this.hdescriptiondata = hdescriptiondata;
    }

    public String getHiddata() {
        return hiddata;
    }

    public void setHiddata(String hiddata) {
        this.hiddata = hiddata;
    }
}