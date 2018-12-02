package com.example.todolist_mvp.presenter;

import com.example.todolist_mvp.data.OneTodo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public interface IPresenter {
    void loadData();
    void saveData(List<OneTodo> content);
    void showDetailData();
}
