package com.example.todolist_mvp.presenter;

import com.example.todolist_mvp.data.OneTodo;
import com.example.todolist_mvp.model.IModel;
import com.example.todolist_mvp.model.Model;
import com.example.todolist_mvp.view.IView;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public class Presenter implements IPresenter, Model.LoadDataCallback{

    private final Model mModel;
    private final IView mView;

    public Presenter(IView mView) {
        this.mView = mView;
        this.mModel = new Model();
    }

    @Override
    public void loadData() {
        mModel.getData(Presenter.this);
    }

    @Override
    public void saveData(List<OneTodo> content) {
        mModel.saveData(content);
    }

    @Override
    public void outPut(List<OneTodo> content) {
        mView.showData(content);

    }
}
