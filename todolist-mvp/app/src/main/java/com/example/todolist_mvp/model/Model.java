package com.example.todolist_mvp.model;

import com.example.todolist_mvp.data.OneTodo;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import com.example.todolist_mvp.Todo;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public class Model implements IModel {
    private List<OneTodo> mContent = new ArrayList<>();
    @Override
    public void getData(LoadDataCallback callback){
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
        callback.outPut(mContent);
    }

    @Override
    public void saveData(List<OneTodo> content) {
        for (OneTodo oneTodo:content) {
            Todo todo = new Todo();
            todo.setContent(oneTodo.getContent());
            todo.setTitle(oneTodo.getTitle());
            todo.setPriority(oneTodo.getPriority());
            todo.save();
        }
    }

    public interface LoadDataCallback{
        void outPut(List<OneTodo> content);
    }
}
