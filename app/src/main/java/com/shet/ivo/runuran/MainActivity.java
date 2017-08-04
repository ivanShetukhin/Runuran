package com.shet.ivo.runuran;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences list_of_ran_distance;
    private EditText input_distance;
    private Button save_btn;
    private Button clear_btn;
    private ScrollView distance_list;

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
        save_btn = (Button) findViewById(R.id.save_btn);
        clear_btn = (Button) findViewById(R.id.clear_btn);
        distance_list = (ScrollView) findViewById(R.id.distance_list);
    }






}
