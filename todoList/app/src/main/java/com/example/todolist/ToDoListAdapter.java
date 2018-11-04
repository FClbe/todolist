package com.example.todolist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private List<String> mToDo;
    static class ViewHolder extends RecyclerView.ViewHolder{
        EditText todoListContent;
        Button button_del;

        public ViewHolder(View view){
            super(view);
            todoListContent = (EditText) view.findViewById(R.id.my_edit);
            button_del = (Button) view.findViewById(R.id.del);
        }
    }

    public ToDoListAdapter (List<String>  toDoList){mToDo = toDoList;}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist, parent, false);
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
