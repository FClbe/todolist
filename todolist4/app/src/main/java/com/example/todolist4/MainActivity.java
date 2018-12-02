package com.example.todolist4;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<OneTodo> mContent = new ArrayList<>();
    public MyAdapter mAdapter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //读取数据库
        List<Todo> todos = DataSupport.findAll(Todo.class);
        for (Todo todo : todos) {
            mContent.add(new OneTodo(todo.getTitle(), todo.getContent(), todo.getPriority()));
        }
        if (mContent.size() == 0) {
            mContent.add(new OneTodo());
            Todo todo = new Todo();
            todo.setContent(mContent.get(0).getContent());
            todo.setTitle(mContent.get(0).getTitle());
            todo.setPriority(mContent.get(0).getPriority());
            todo.save();
        }

        initView();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(MainActivity.this, mContent);
        recyclerView.setAdapter(mAdapter);
        Button add = (Button) findViewById(R.id.button_add);
        Button button_fen1 = (Button) findViewById(R.id.fen1);
        Button button_fen30 = (Button) findViewById(R.id.fen30);
        Button button_fen60 = (Button) findViewById(R.id.fen60);
        Button button_day1 = (Button) findViewById(R.id.day1);
        FloatingActionButton add1 = (FloatingActionButton)findViewById(R.id.add1);
        add1.setOnClickListener(this);
        add.setOnClickListener(this);
        button_fen1.setOnClickListener(this);
        button_fen30.setOnClickListener(this);
        button_fen60.setOnClickListener(this);
        button_day1.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.nav_compare);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return false;
            }
        });

    }

    private void initView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String content = data.getStringExtra("returnContent");
                    String title = data.getStringExtra("returnTitle");
                    int position = data.getIntExtra("returnPosition", 0);
                    System.out.println(position + content);
                    new OneTodo(title, content);
                    mContent.get(position).setContent(content);
                    mContent.get(position).setTitle(title);
                    mAdapter.notifyDataSetChanged();
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除， 添加数据
        DataSupport.deleteAll(Todo.class);
        for (int i = 0; i < mAdapter.getmToDo().size(); i++) {
            Todo todo = new Todo();
            todo.setContent(mContent.get(i).getContent());
            todo.setTitle(mContent.get(i).getTitle());
            todo.setPriority(mContent.get(i).getPriority());
            todo.save();
        }

    }

    @Override
    public void onClick(View v)
    {
        final AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final Intent intentA = new Intent(this, MyService.class);
        final PendingIntent pi = PendingIntent.getService(this, 0, intentA, 0);
        long triggerAtTime;
        switch(v.getId()){
            case R.id.add1:
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.add_menu);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.A:
                                mAdapter.getmToDo().add(new OneTodo(4));
                                mAdapter.notifyItemInserted(mAdapter.getmToDo().size() - 1);
                                mAdapter.notifyItemRangeChanged(0, mAdapter.getmToDo().size() - 1);
                                break;
                            case R.id.B:
                                mAdapter.getmToDo().add(new OneTodo(3));
                                mAdapter.notifyItemInserted(mAdapter.getmToDo().size() - 1);
                                mAdapter.notifyItemRangeChanged(0, mAdapter.getmToDo().size() - 1);
                                break;
                            case R.id.C:
                                mAdapter.getmToDo().add(new OneTodo(2));
                                mAdapter.notifyItemInserted(mAdapter.getmToDo().size() - 1);
                                mAdapter.notifyItemRangeChanged(0, mAdapter.getmToDo().size() - 1);
                                break;
                            case R.id.D:
                                mAdapter.getmToDo().add(new OneTodo(1));
                                mAdapter.notifyItemInserted(mAdapter.getmToDo().size() - 1);
                                mAdapter.notifyItemRangeChanged(0, mAdapter.getmToDo().size() - 1);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                //mAdapter.getmToDo().add(new OneTodo());
                //mAdapter.notifyItemInserted(mAdapter.getmToDo().size() - 1);
                //mAdapter.notifyItemRangeChanged(0, mAdapter.getmToDo().size() - 1);
                break;
            case R.id.button_add:
                mAdapter.getmToDo().add(new OneTodo());
                mAdapter.notifyItemInserted(mAdapter.getmToDo().size() - 1);
                mAdapter.notifyItemRangeChanged(0, mAdapter.getmToDo().size() - 1);
                break;
            case R.id.fen1:
                triggerAtTime = System.currentTimeMillis() + 10 * 1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fen30:
                triggerAtTime = System.currentTimeMillis() + 30 * 60 * 1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fen60:
                triggerAtTime = System.currentTimeMillis() + 60 * 60 * 1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day1:
                triggerAtTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
                manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
                Toast.makeText(MainActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.main_compare:
                prioritySort(mContent);
                mAdapter.notifyDataSetChanged();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    public void prioritySort(List<OneTodo> a){
        int gap = a.size() / 2;
        for(;gap >= 1; gap /= 2)
        for(int i = gap; i < a.size(); i++)
            for (int j = i - gap; j >= 0 && oneToDoCompare(a.get(j), a.get(j + gap) ) > 0; j = j - gap) {
                OneTodo temp = new OneTodo(a.get(j + gap).getTitle(),
                        a.get(j + gap).getContent(), a.get(j + gap).getPriority() );
                a.get(j + gap).setTitle(a.get(j).getTitle());
                a.get(j + gap).setContent(a.get(j).getContent());
                a.get(j + gap).setPriority(a.get(j).getPriority());
                a.get(j).setTitle(temp.getTitle());
                a.get(j).setContent(temp.getContent());
                a.get(j).setPriority(temp.getPriority());
            }
    }
    public int oneToDoCompare(OneTodo todo1, OneTodo todo2){
        if (todo1.getPriority() > todo2.getPriority())
            return -1;
        else if(todo1.getPriority() < todo2.getPriority())
            return 1;
        else
           // return todo2.getTitle().compareTo(todo1.getTitle());
            return  0;

        /*{
            if(todo2.getTitle().equals(null) && !todo1.getTitle().equals(null))
                return -1;
            if(!todo2.getTitle().equals(null) && todo1.getTitle().equals(null))
                return 1;
            if(todo2.getTitle().equals(null) && todo1.getTitle().equals(null))
                return 0;
            else
                return todo2.getTitle().compareTo(todo1.getTitle());
        }*/




    }
}
