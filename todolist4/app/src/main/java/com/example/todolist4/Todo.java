package com.example.todolist4;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/11/8 0008.
 */

public class Todo extends DataSupport {
    private int id;
    private String title;
    private String content;
    private int priority;
    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {

        return content;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
