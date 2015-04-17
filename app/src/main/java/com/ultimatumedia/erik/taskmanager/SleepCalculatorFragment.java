package com.ultimatumedia.erik.taskmanager;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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
        final View view = inflater.inflate(R.layout.fragment_sleep_calculator, container, false);

        final Button timeNeededToWakeUp = (Button) view.findViewById(R.id.TimeNeededToWakeUp);
        final Button hoursNeededToSleep = (Button) view.findViewById(R.id.HoursNeededToSleep);
        final TextView timeToGoToSleep = (TextView) view.findViewById(R.id.TimeToGoToSleep);

        timeNeededToWakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] time = timeNeededToWakeUp.getText().toString().split(":");
                int hourToWakeUp = Integer.parseInt(time[0]);
                time = time[1].split(" ");
                int minuteToWakeUp = Integer.parseInt(time[0]);
                TimePickerDialog tpd = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeNeededToWakeUp.setText(FormatTime(false, hourOfDay, minute));
                        String[] time = hoursNeededToSleep.getText().toString().split(":");
                        int hourSleepNeeded = Integer.parseInt(time[0]);
                        time = time[1].split(" ");
                        int minuteSleepNeeded = Integer.parseInt(time[0]);
                        timeToGoToSleep.setText(CalculateTimeToSleep(false, hourOfDay, minute, hourSleepNeeded, minuteSleepNeeded));
                    }
                }, hourToWakeUp, minuteToWakeUp, false);
                tpd.setTitle("Set Time Needed To Wake Up");
                tpd.show();
            }
        });

        hoursNeededToSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] time = hoursNeededToSleep.getText().toString().split(":");
                int hourSleepNeeded = Integer.parseInt(time[0]);
                time = time[1].split(" ");
                int minuteSleepNeeded = Integer.parseInt(time[0]);
                TimePickerDialog tpd = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hoursNeededToSleep.setText(FormatTime(true, hourOfDay, minute));
                        String[] time = timeNeededToWakeUp.getText().toString().split(":");
                        int hourToWakeUp = Integer.parseInt(time[0]);
                        time = time[1].split(" ");
                        int minuteToWakeUp = Integer.parseInt(time[0]);
                        timeToGoToSleep.setText(CalculateTimeToSleep(false, hourToWakeUp, minuteToWakeUp, hourOfDay, minute));
                    }
                }, hourSleepNeeded, minuteSleepNeeded, true);
                tpd.setTitle("Set Time Needed To Sleep");
                tpd.show();
            }
        });
        return view;
    }

    public String FormatTime(Boolean is24Hour, int hour, int minute) {
        Log.i("INFO (erik)", "Formating Time is24Hour:" + is24Hour.toString() + " hour:" + hour + " minute" + minute);
        String sHour = Integer.toString(hour);
        String sMinute = Integer.toString(minute);
        String ap = "";

        if (minute < 10) {
            Log.i("INFO (erik)", "Minute is less then 10 adding a 0 to the front");
            sMinute = "0" + minute;
        }
        if (!is24Hour) {
            Log.i("INFO (erik)", "Is Not 24 hour Format Setting to AM");
            ap = "AM";
            if (hour == 0) {
                Log.i("INFO (erik)", "Hour is 0 changing time to 12 and is AM");
                hour = 12;
                sHour = Integer.toString(hour);
                ap = "AM";
            }
            if (hour > 12) {
                Log.i("INFO (erik)", "Hour is greater then 12 changing time to hour - 12 and is PM");
                hour -= 12;
                sHour = Integer.toString(hour);
                ap = "PM";
            }
        }
        Log.i("INFO (erik)", "Time formated to " + sHour + ":" + sMinute + " " + ap);
        return sHour + ":" + sMinute + " " + ap;
    }

    private String CalculateTimeToSleep(boolean is24Hour, int hourSleepNeeded, int minuteSleepNeeded, int hourToWakeUp, int minuteToWakeUp) {
        Log.i("INFO (erik)", "Calculating Time That you need to go to bed is24Hour:" + is24Hour + " hoursOfSleepNeeded:" + hourSleepNeeded + " minutesOfSleepNeeded:" + minuteSleepNeeded + " hourToWakeUp:" + hourToWakeUp + " minuteToWakeUp:" + minuteToWakeUp);
        int hourTimeToSleep = hourSleepNeeded - hourToWakeUp;
        int minuteTimeToSleep = minuteSleepNeeded - minuteToWakeUp;
        String ap = "";

        if (hourTimeToSleep < 0) {
            Log.i("INFO (erik)", "Hour is Calculated to be less then 0 or previous day adding 24 Hours to Hour");
            hourTimeToSleep += 24;
        }

        if (minuteTimeToSleep < 0) {
            Log.i("INFO (erik)", "Minute is Calculated to be less then 0 Subtract Hour and add 60 Minutes");
            hourTimeToSleep--;
            minuteTimeToSleep += 60;
        }
        String FormatedTime = FormatTime(is24Hour, hourTimeToSleep, minuteTimeToSleep);

        Log.i("INFO (erik)", "Returning " + FormatedTime);
        return FormatedTime;
    }

}
