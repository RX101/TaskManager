package com.example.a15041867.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> taskList;
    ArrayAdapter aa;
    ListView lv;
    Button btnAddTask;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<Task>();
        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        db = new DBHelper(MainActivity.this);
        lv = (ListView)findViewById(R.id.lv);

        taskList = db.getTasks();
        db.close();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(i,123);


            }
        });

        aa = new ArrayAdapter<Task>(MainActivity.this,
                android.R.layout.simple_list_item_1, taskList);
        lv.setAdapter(aa);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 123 ) {
            DBHelper db = new DBHelper(MainActivity.this);
            taskList = db.getTasks();
            aa = new ArrayAdapter<Task>(MainActivity.this,
                    android.R.layout.simple_list_item_1, taskList);
            lv.setAdapter(aa);
        }
    }
}
