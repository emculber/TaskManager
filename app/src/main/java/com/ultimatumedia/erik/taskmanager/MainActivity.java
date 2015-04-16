package com.ultimatumedia.erik.taskmanager;

import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
