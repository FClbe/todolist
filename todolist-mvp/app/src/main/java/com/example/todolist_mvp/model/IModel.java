package com.example.todolist_mvp.model;

import com.example.todolist_mvp.data.OneTodo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public interface IModel {
    void getData(Model.LoadDataCallback callback);
    void saveData(List<OneTodo> content);
}
