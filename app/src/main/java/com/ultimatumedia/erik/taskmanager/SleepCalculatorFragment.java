package com.ultimatumedia.erik.taskmanager;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SleepCalculatorFragment extends Fragment {

    public SleepCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_calculator, container, false);
    }


    final Button timeNeededToWakeUp = (Button)findViewById(R.id.TimeNeededToWakeUp);
    final Button hoursNeededToSleep = (Button)findViewById(R.id.HoursNeededToSleep);
    final TextView timeToGoToSleep = (TextView)findViewById(R.id.TimeToGoToSleep);

    timeNeededToWakeUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] time = timeNeededToWakeUp.getText().toString().split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            TimePickerDialog tpd = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //timeNeededToWakeUp.setText(hourOfDay + ":" + sMinute);
                    //timeToGoToSleep.setText(hourTimeToSleep + ":" + sMinute + ap);
                }
            }, hour, minute, false);
            tpd.setTitle("Set Time Needed To Wake Up");
            tpd.show();
        }
    });

    hoursNeededToSleep.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] time = hoursNeededToSleep.getText().toString().split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            TimePickerDialog tpd = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //hoursNeededToSleep.setText(hourOfDay + ":" + sMinute);
                    //timeToGoToSleep.setText(FormatTime(true, hourOfDay, minute);
                }
            }, hour, minute, true);
            tpd.setTitle("Set Time Needed To Sleep");
            tpd.show();
        }
    });

}

    public String FormatTime(Boolean is24Hour, int hour, int minute) {
        String sHour = Integer.toString(hour);
        String sMinute = Integer.toString(minute);
        String ap = "";

        if(minute < 10){
            sMinute = "0" + minute;
        }
        if(is24Hour) {
            if (hour == 0) {
                hour = 12;
                sHour = Integer.toString(hour);
                ap = "AM";
            }
            if (hour > 12) {
                hour -= 12;
                sHour = Integer.toString(hour);
                ap = "PM";
            }
        }
        return sHour + ":" + sMinute + " " + ap;
    }

    private String CalculateTimeToSleep(int hourSleepNeeded, int minuteSleepNeeded, int hourToWakeUp, int minuteToWakeUp) {
        int hourTimeToSleep = hourSleepNeeded - hourToWakeUp;
        int minuteTimeToSleep = minuteSleepNeeded - minuteToWakeUp;
        String ap = "";

        if(hourTimeToSleep < 0) {
            hourTimeToSleep += 24;
        }

        if(minuteTimeToSleep < 0) {
            minuteTimeToSleep += 60;
        }
        return hourTimeToSleep + ":" + minuteTimeToSleep + " " + ap;
    }

}
