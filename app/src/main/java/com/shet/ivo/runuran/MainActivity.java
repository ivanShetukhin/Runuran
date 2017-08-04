package com.shet.ivo.runuran;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences list_of_ran_distance;
    private EditText input_distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //search have ran distance
        list_of_ran_distance = getSharedPreferences("distance", MODE_PRIVATE);
    }
    //set elements
    private void setElements()
    {
        input_distance = (EditText) findViewById(R.id.input_distance);
    }






}
