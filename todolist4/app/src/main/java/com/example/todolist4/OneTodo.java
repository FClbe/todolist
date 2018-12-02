package com.example.todolist4;

/**
 * Created by Administrator on 2018/11/11 0011.
 */

public class OneTodo {
    private String title;
    private String content;
    private int priority;

    public OneTodo() {
        content = "";
        title = "";
        priority = 1;
    }

    public OneTodo(String content) {
        this.content = content;
        title = "";
        priority = 1;
    }

    public OneTodo(int priority) {
        this.title = "";
        this.content = "";
        this.priority = priority;
    }

    public OneTodo(String title, String content) {
        this.content = content;
        this.title = title;
        priority = 1;
    }

    public OneTodo(String content,String title , int priority) {
        this.content = content;
        this.priority = priority;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
