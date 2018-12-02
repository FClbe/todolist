package com.example.todolist_mvp;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public class Todo extends DataSupport{
    private int id;
    private String title;
    private String content;
    private int priority;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
