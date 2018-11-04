package com.example.todolist;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MyList> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mylistImage;
        TextView mylistName;

        public ViewHolder(View view){
            super(view);
            mylistImage = (ImageView) view.findViewById(R.id.my_image);
            mylistName = (TextView) view.findViewById(R.id.my_name);
        }
    }

    public MyAdapter(List<MyList> myList){
        mList = myList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kapai, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        MyList myList = mList.get(position);
        holder.mylistImage.setImageResource(myList.getImageId());
        holder.mylistName.setText(myList.getName());
    }
    @Override
    public int getItemCount(){
        return mList.size();
    }
}
