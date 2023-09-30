package com.example.alarmmanagerdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alarmmanagerdemo.MyAlarm;
import com.example.alarmmanagerdemo.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //declaring variables
    private Button setAlarmButton;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding the timePicker variable with the TimePicker View
        timePicker = findViewById(R.id.timePicker);
        //binding the setAlarmButton variable with the Schedule Alarm Button
        setAlarmButton = findViewById(R.id.alarmBtn);

        //adding on click listener on our setAlarmButton
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            long time;
            @Override
            public void onClick(View v) {
                //creating an instance of the calendar class
                Calendar calendar = Calendar.getInstance();
                //setting up the calendar
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                } else {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );
                }

                android.util.Log.d("AlarmTime", "Scheduled time: " + calendar.getTimeInMillis());
                setAlarm(calendar.getTimeInMillis());
            }
        });
    }

    private void setAlarm(long timeInMillis) {
        //creating the AlarmManager object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //Creating an intent to the MyAlarm Class
        Intent intent = new Intent(this, MyAlarm.class);

        //setting up a pending intent
        PendingIntent yourPendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                AlarmManager.INTERVAL_DAY,
                yourPendingIntent
        );

        //displaying the user that the alarm is scheduled
        Toast.makeText(this, "You Alarm is Scheduled", Toast.LENGTH_SHORT).show();
    }

}
