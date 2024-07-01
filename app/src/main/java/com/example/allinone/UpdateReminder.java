package com.example.allinone;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateReminder extends Fragment {

    View view;
    Button up_btn_date, up_btn_time, up_btn_save, up_btn_cancel;
    EditText up_edt_title, up_edt_description;
    Database database;
    String timeTonotify1;
    String titledata, descriptiondata, datedata, timedata, iddata;
    int uiddata;
    String timeTonotify;
    RAdapter RAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_reminder, container, false);

        titledata = ((MainActivity) requireActivity()).getClocktitledata();
        descriptiondata = ((MainActivity) requireActivity()).getClockdescriptiondata();
        datedata = ((MainActivity) requireActivity()).getClockdatedata();
        timedata = ((MainActivity) requireActivity()).getClocktimedata();
        iddata = ((MainActivity) requireActivity()).getClockiddata();
        uiddata = ((MainActivity) requireActivity()).getClockuniddata();

        up_btn_date = (Button) view.findViewById(R.id.up_btn_date);
        up_btn_time = (Button) view.findViewById(R.id.up_btn_time);
        up_btn_save = (Button) view.findViewById(R.id.up_btn_save);
        up_btn_cancel = (Button) view.findViewById(R.id.up_btn_cancel);
        up_edt_title = (EditText) view.findViewById(R.id.up_edt_title);
        up_edt_description = (EditText) view.findViewById(R.id.up_edt_description);

        database = new Database(getActivity());

        up_edt_title.setText(titledata);
        up_edt_description.setText(descriptiondata);
        up_btn_date.setText(datedata);
        up_btn_time.setText(timedata);

        up_btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();
            }
        });

        up_btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });

        up_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(uiddata);
            }
        });

        up_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = up_edt_title.getText().toString().trim();
                String description = up_edt_description.getText().toString().trim();
                String date = up_btn_date.getText().toString().trim();
                String time = up_btn_time.getText().toString().trim();

                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter the text", Toast.LENGTH_SHORT).show();
                } else {
                    if (time.equals("time") || date.equals("date")) {
                        Toast.makeText(getActivity(), "Please select date and time", Toast.LENGTH_SHORT).show();
                    } else {
                        int uid = (int) System.currentTimeMillis();
                        processinsert(title, description, date, time, uid);
                    }
                }
            }
        });

        return view;
    }

    private void processinsert(String title, String description, String date, String time, int uid) {
        database.addNewHeatblast(title, description, date, time, uid);
        setAlarm(title, description, date, time, uid);
    }

    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;
                up_btn_time.setText(FormatTime(i, i1));
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                up_btn_date.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }

        return time;
    }


    private void setAlarm(String title, String description, String date, String time, int uid) {
        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getActivity(), AlarmBrodcast.class);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("uid",uid);

        //@SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), uid, intent, PendingIntent.FLAG_ONE_SHOT);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), uid, intent, 0);
        String dateandtime = date + " " + timeTonotify;
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            assert date1 != null;
            alarmManager.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            Toast.makeText(getActivity(), "Alarm", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        database.deleteSingleHeatblast(iddata);
        ((MainActivity) requireActivity()).replaceFragments(ViewReminders.class);
    }

    public void delete(int uid){
        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmBrodcast.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), uid, intent, 0);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    //handling on back button pressed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) requireActivity()).replaceFragments(ViewReminders.class);
            }
        });
    }
}