package com.example.todolist_mvp.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.todolist_mvp.Todo;
import com.example.todolist_mvp.data.OneTodo;

import com.example.todolist_mvp.R;
import com.example.todolist_mvp.presenter.Presenter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener{

    private RecyclerView recyclerView;
    public TodoAdapter mAdapter;
    private DrawerLayout mDrawerLayout;
    private List<OneTodo> mContent = new ArrayList<>();
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        mPresenter = new Presenter(MainActivity.this);
        mPresenter.loadData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String content = data.getStringExtra("returnContent");
                    String title = data.getStringExtra("returnTitle");
                    int position = data.getIntExtra("returnPosition", 0);
                    System.out.println(position + content);
                    mContent.get(position).setContent(content);
                    mContent.get(position).setTitle(title);
                    mAdapter.notifyDataSetChanged();
                }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        DataSupport.deleteAll(Todo.class);
        returnData();
    }

    @Override
    public void showData(List<OneTodo> content) {
       mContent = content;
        mAdapter = new TodoAdapter(MainActivity.this, mContent);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void returnData() {
        mPresenter.saveData(mContent);

    }

    @Override
    public void showDetail() {

    }

    public void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    }

    @Override
    public void onClick(View v)
    {
        final AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final Intent intentA = new Intent(this, TodoService.class);
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
}
