package com.example.patrick.tasklist;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;


public class new_task extends Activity {
    Button btnSelectTime;
    static final int TIME_DIALOG_ID=1;
    public int hour, minute;
    private int mHour, mMinute;
    EditText whoEditText, whatEditText, desEditText;
    String who,what, des;
    int hourz, minz;

    public new_task()
    {
        // Assign current Date and Time Values to Variables
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        btnSelectTime=(Button)findViewById(R.id.button3);
        whoEditText = (EditText)findViewById(R.id.editText);
        whatEditText = (EditText)findViewById(R.id.editText2);
        desEditText = (EditText)findViewById(R.id.editText3);
        if(savedInstanceState!=null)
        {
            who = savedInstanceState.getString("who");
            what= savedInstanceState.getString("what");
            des = savedInstanceState.getString("des");
            hourz = savedInstanceState.getInt("hr");
            minz = savedInstanceState.getInt("min");
        }
        if(who != null)
            whoEditText.setText(who);
        if(what != null)
            whatEditText.setText(what);
        if(des != null)
            desEditText.setText(des);
        if (hourz != 0 && minz !=0) {
            hour = hourz;
            minute = minz;
            btnSelectTime.setText(hour + ":" + minute);
        }
        btnSelectTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the TimePickerDialog
                showDialog(TIME_DIALOG_ID);
            }
        });
    }
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    hour = hourOfDay;
                    minute = min;
                    // Set the Selected Date in Select date Button
                    btnSelectTime.setText(hour+":"+minute);
                }
            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinute, false);

        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
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

    protected void onSaveInstanceState (Bundle outState)
    {
        outState.putString("who", whoEditText.getText().toString());
        outState.putString("what",  whatEditText.getText().toString());
        outState.putString("des", desEditText.getText().toString());
        outState.putString("description", "meow");
        outState.putInt("hr", hour);
        outState.putInt("min", minute);
    }

}
