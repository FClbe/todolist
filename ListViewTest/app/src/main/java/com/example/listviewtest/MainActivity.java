 package com.example.listviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
    public MyAdapter adapter1;
     private TextView text;
    private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon",
            "Apple", "Banana", "Orange", "Watermelon"
    };
     private static ArrayList<String> todoList = new ArrayList<>();
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         switch(requestCode){
             case 1:
                 if(resultCode == RESULT_OK){
                     String returnedData = data.getStringExtra("data_return");
                     todoList.add(returnedData);
                 }
                 break;
             default:
         }
     }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*todoList.add("asda");
         final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                 MainActivity.this, R.layout.todolist, todoList);*/
         ListView listView = (ListView) findViewById(R.id.list_view);
         adapter1 = new MyAdapter(this);
         listView.setAdapter(adapter1);
        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

               adapter1.arr.add(" ");
                adapter1.notifyDataSetChanged();
                //Intent intent = new Intent(MainActivity.this, makeOneTodoList.class);
               //startActivityForResult(intent, 1);
               // Log.d("MainActivity", todoList.toString());

            }
        });

    }
     private class MyAdapter extends BaseAdapter{
         private Context context;
         private LayoutInflater inflater;
         public ArrayList<String> arr;
         public MyAdapter(Context context){
             super();
             this.context = context;
             inflater = LayoutInflater.from(context);
             arr = new ArrayList<String>();
             for(int i = 0;i < 3;i++)
             {
                 arr.add("");
             }
         }
         @Override
         public int getCount(){
             return  arr.size();
         }
         @Override
         public Object getItem(int arg0){
             return arg0;
         }
         @Override
         public long getItemId(int arg0){
             return arg0;
         }
         @Override
         public View getView(final int position, View view, ViewGroup arg2){
             if(view == null){
                 view = inflater.inflate(R.layout.todolist, null);
             }
                 final EditText edit = (EditText) view.findViewById(R.id.edit_2);
                edit.setText(arr.get(position));
             Button del = (Button) view.findViewById(R.id.button_del);
             edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                 @Override
                 public void onFocusChange(View v, boolean hasFocus) {
                     if(arr.size() > 0 && position < arr.size() - 1){
                         arr.set(position, edit.getText().toString());
                     }
                 }
             });
             del.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     arr.set(position, "");
                     arr.remove(position);
                     adapter1.notifyDataSetChanged();
                 }
             });
             return view;
         }
     }
}
