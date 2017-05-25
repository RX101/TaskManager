package com.example.a15041867.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    Intent i;
    EditText etName, etDescription, etRemind;
    Button btnAddtask2, btnCancel;
    DBHelper db;
    int reqCode = 12345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText)findViewById(R.id.ediTextName);
        etDescription = (EditText)findViewById(R.id.ediTextDescription);
        btnAddtask2 = (Button)findViewById(R.id.btnAddTask2);
        etRemind = (EditText)findViewById(R.id.editTextRemind);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        db = new DBHelper(AddActivity.this);

        i = getIntent();

        btnAddtask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String desc = etDescription.getText().toString();
                int remind = Integer.parseInt(etRemind.getText().toString());
                Task task1 = new Task(name,desc);
                db.insertTask(task1);
                db.close();
                setResult(RESULT_OK, i);
                finish();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, remind);

                Intent intent = new Intent(AddActivity.this,
                        MyReceiver.class);
                intent.putExtra("Task",task1);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, i);
                finish();
            }
        });




    }
}
