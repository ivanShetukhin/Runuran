package com.shet.ivo.runuran;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;                          //for current date in version until 24

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MESSAGE!";
    private SharedPreferences list_of_ran_distance;
    private EditText input_distance;
    private Button save_btn;
    private Button clear_btn;
    private ScrollView distance_list;
    private String current_date;
    private LinearLayout distance_list_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setElements();                                  //set elements activity_main
        setListenerToButtons();                         //set listeners to screen button


        //search have ran distance
        list_of_ran_distance = getSharedPreferences("distance", MODE_PRIVATE);
        refreshDistanceRan(null);
    }
    //set elements
    private void setElements()
    {
        input_distance = (EditText) findViewById(R.id.input_distance);
        save_btn = (Button) findViewById(R.id.save_btn);
        clear_btn = (Button) findViewById(R.id.clear_btn);
        distance_list_layout = (LinearLayout) findViewById(R.id.distance_list_layout);
    }

    private void setListenerToButtons()
    {
        save_btn.setOnClickListener(saveButtonListener);
        clear_btn.setOnClickListener(clearButtonListener);
    }

    //check current date
    private String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        String current_date = sdf.format(c.getTime());
        Log.i(TAG, current_date);
        return current_date;
    }

    private void refreshDistanceRan(String new_distance)
    {
        //get shared preference
        String[] distance_list = list_of_ran_distance.getAll().keySet().toArray(new String[]);
        if (new_distance != null)
        {
            makeDistanceGUI(new_distance, Arrays.binarySearch(distance_list, new_distance));
        }
        else
        {
            for (int index = 0; index < distance_list.length; ++index)
            {
                makeDistanceGUI(distance_list[index], index);
            }
        }
    }

    private void makeDistanceGUI(String distance, int index)
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View newDistanceView = inflater.inflate(R.layout.distance_and_date, null);
        TextView distance_text = (TextView) findViewById(R.id.distance_item);
        distance_text.setText(distance);
        TextView distance_date = (TextView) findViewById(R.id.distance_date);
        distance_date.setText(getCurrentDate());
        distance_list_layout.addView(newDistanceView, index);
    }

    private void clearMethod()
    {
        distance_list_layout.removeAllViews();
    }

    //add valuer to shared preference
    private void makeDistance(String distance, String current_date)
    {
        SharedPreferences.Editor preferenceEditor = list_of_ran_distance.edit();
        preferenceEditor.putString(distance, current_date);
        preferenceEditor.apply();
    }


    public View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (input_distance.length() > 0)
            {
                makeDistance(input_distance.getText().toString(), getCurrentDate());
                input_distance.setText("");
                //hide the keyboard
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(input_distance.getWindowToken(), 0);
            }
            else
            {
                //to create dialog about to need make the input
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("hey! runner! ");
                builder.setPositiveButton("OK", null);
                builder.setMessage("to need input distance");
                AlertDialog errorDialog = builder.create();
                errorDialog.show();

            }
        }
    }

}
