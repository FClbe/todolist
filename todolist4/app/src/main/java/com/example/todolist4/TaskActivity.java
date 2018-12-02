package com.example.todolist4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTitle;
    private EditText editContent;
    private Intent receiveIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_task);
        FloatingActionButton check = (FloatingActionButton) findViewById(R.id.check);
        FloatingActionButton addAlarm = (FloatingActionButton) findViewById(R.id.add_alarm);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editContent = (EditText) findViewById(R.id.editContent) ;
        check.setOnClickListener(this);
        addAlarm.setOnClickListener(this);
        receiveIntent = getIntent();
        String title = receiveIntent.getStringExtra("viewTitle");
        String content = receiveIntent.getStringExtra("viewContent");
        editTitle.setText(title);
        editContent.setText(content);
    }

    @Override
    public void onClick(View v)
    {
        final AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final Intent intentA = new Intent(this, MyService.class);
        final PendingIntent pi = PendingIntent.getService(this, 0, intentA, 0);
        switch (v.getId()){
            case R.id.check:
                OneTodo oneTodo = new OneTodo();
                oneTodo.setTitle(editTitle.getText().toString());
                int position = receiveIntent.getIntExtra("position",0);
                oneTodo.setContent(editContent.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("returnTitle", oneTodo.getTitle());
                intent.putExtra("returnContent", oneTodo.getContent());
                intent.putExtra("returnPosition", position);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.add_alarm:
                final Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                new TimePickerDialog(TaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
                        .show();
                break;
            default:
                break;
        }
    }
}
