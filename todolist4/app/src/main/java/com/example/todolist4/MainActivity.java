package com.example.todolist4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private List<MyList> myLists = new ArrayList<>();
    private List<String> myContent = new ArrayList<>();
    public MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyService.class);
        final PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);

        //读取数据库
        List<Todo> todos = DataSupport.findAll(Todo.class);
        for(Todo todo: todos){
            myContent.add(todo.getContent());
        }
        if(myContent.size() == 0)
        {
            myContent.add(" ");
            Todo todo  = new Todo();
            todo.setContent(myContent.get(0));
            todo.save();
        }

        //initHeroes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myContent);
        recyclerView.setAdapter(mAdapter);
        Button add = (Button) findViewById(R.id.button_add);
        Button button_fen1 = (Button) findViewById(R.id.fen1);
        Button button_fen30 = (Button) findViewById(R.id.fen30);
        Button button_fen60 = (Button) findViewById(R.id.fen60);
        Button button_day1 = (Button) findViewById(R.id.day1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getmToDo().add(" ");
                mAdapter.notifyItemInserted(mAdapter.getmToDo().size()-1);
                mAdapter.notifyItemRangeChanged(0,mAdapter.getmToDo().size()-1);
            }
        });
        button_fen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              long triggerAtTime = System.currentTimeMillis() + 1*1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
            }
        });
        button_fen30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtTime = System.currentTimeMillis() + 30*60*1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
            }
        });
        button_fen60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtTime = System.currentTimeMillis() + 60*60*1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
            }
        });
        button_day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerAtTime = System.currentTimeMillis() + 24*60*60*1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //删除， 添加数据
        Log.d("data", "6666666666666666666");
        DataSupport.deleteAll(Todo.class);
        Log.d("data", "6666666666666666666");
        for(int i = 0;i < mAdapter.getmToDo().size(); i++)
        {
            Todo todo  = new Todo();
            todo.setContent(myContent.get(i));
            todo.save();
        }

    }


    /*private void initHeroes(){
        for (int i = 0; i < 20; i++){
            MyList hero = new MyList("kapai", R.drawable.img_1);
            myLists.add(hero);
        }
    }*/
}
