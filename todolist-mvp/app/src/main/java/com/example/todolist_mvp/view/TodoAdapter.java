package com.example.todolist_mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.todolist_mvp.R;
import com.example.todolist_mvp.data.OneTodo;
import com.example.todolist_mvp.presenter.Presenter;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

    public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>implements IView {
    //AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    private List<OneTodo> mToDo;
    private Activity mActivity;
    private Presenter mPresenter = new Presenter(TodoAdapter.this);

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView todoListContent;
        TextView todoListTitle;
        Button button_del;
        View todoView;
        //Button button_fen1;
        //Button button_fen30;
        //Button button_fen60;


        public ViewHolder(View view) {
            super(view);
            todoView = view;
            todoListContent = (TextView) view.findViewById(R.id.content);
            button_del = (Button) view.findViewById(R.id.del);
            todoListTitle = (TextView) view.findViewById(R.id.todolisttitle);
            // button_fen1 = (Button) view.findViewById(R.id.fen1);
            //button_fen30 = (Button) view.findViewById(R.id.fen30);
            //button_fen60 = (Button) view.findViewById(R.id.fen60);
        }
    }


    public TodoAdapter(Activity activity, List<OneTodo> toDoList) {
        mToDo = toDoList;
        mActivity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        OneTodo oneTodo = mToDo.get(position);
        if (position >= 0) {
            String context = mToDo.get(position).getContent();
            holder.todoListContent.setText(context);
            String title = mToDo.get(position).getTitle();
            holder.todoListTitle.setText(title);
        }
        if (oneTodo.getPriority() == 0)
            holder.itemView.setBackgroundColor(Color.GRAY);
        else if (oneTodo.getPriority() == 1)
            holder.itemView.setBackgroundColor(Color.WHITE);
        else if (oneTodo.getPriority() == 2)
            holder.itemView.setBackgroundColor(Color.GREEN);
        else if (oneTodo.getPriority() == 3)
            holder.itemView.setBackgroundColor(Color.BLUE);
        else
            holder.itemView.setBackgroundColor(Color.RED);
        holder.button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position >= 0 && position <= mToDo.size() - 1) {
                    Log.d("data", mToDo.toString());
                    mToDo.remove(position);
                    //Log.d("data", mToDo.get(position - 1).getContent());
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(0, mToDo.size() - 1);
                }
            }
        });
        holder.todoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, TaskActivity.class);
                intent.putExtra("position", position);
                System.out.println(position);
                intent.putExtra("viewContent", mToDo.get(position).getContent());
                intent.putExtra("viewTitle", mToDo.get(position).getTitle());
                mActivity.startActivityForResult(intent, 1);
            }
        });
        holder.todoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "Finish?", Snackbar.LENGTH_SHORT)
                        .setAction("finish", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mToDo.get(position).setPriority(0);
                                notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDo == null ? 0 : mToDo.size();
    }

    public List<OneTodo> getmToDo() {
        return mToDo;
    }

    @Override
    public void showData(List<OneTodo> content) {}

    @Override
    public void showDetail() {

    }

    @Override
    public void returnData() {}
}
