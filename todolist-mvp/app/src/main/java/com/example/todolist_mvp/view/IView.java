package com.example.todolist_mvp.view;

import com.example.todolist_mvp.data.OneTodo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public interface IView {
    void showData(List<OneTodo> content);
    void returnData();
    void showDetail();
}
