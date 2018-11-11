package com.example.todolist4;

import android.app.AlarmManager;
import android.content.Context;
import android.provider.AlarmClock;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    private List<String> mToDo;
    static class ViewHolder extends RecyclerView.ViewHolder{
        EditText todoListContent;
        Button button_del;
        //Button button_fen1;
        //Button button_fen30;
        //Button button_fen60;


        public ViewHolder(View view){
            super(view);
            todoListContent = (EditText) view.findViewById(R.id.my_edit);
            button_del = (Button) view.findViewById(R.id.del);
           // button_fen1 = (Button) view.findViewById(R.id.fen1);
            //button_fen30 = (Button) view.findViewById(R.id.fen30);
            //button_fen60 = (Button) view.findViewById(R.id.fen60);
        }
    }



    public MyAdapter (List<String>  toDoList){mToDo = toDoList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist4, parent, false);
        final ViewHolder  holder = new ViewHolder(view);
        holder.todoListContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int position = holder.getAdapterPosition();
                System.out.println("edit"+position);
                if(mToDo.size() > 0 && position >= 0){
                    mToDo.set(position, holder.todoListContent.getText().toString());}
            }
        });
        holder.button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(position >= 0) {
                    mToDo.set(position, " ");
                    Log.d("data", mToDo.toString());
                    mToDo.remove(position);
                    Log.d("data", mToDo.toString());
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(0, mToDo.size() - 1);
                    System.out.println("button"+position);
                }
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        if(position >= 0) {
            String context = mToDo.get(position);
            holder.todoListContent.setText(context);
        }
    }
    @Override
    public int getItemCount(){
        return mToDo.size();
    }

    public List<String> getmToDo() {
        return mToDo;
    }

}
