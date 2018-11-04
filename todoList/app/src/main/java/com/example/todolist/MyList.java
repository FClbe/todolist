package com.example.todolist;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class MyList {

    private String name;
    private int imageId;
    public MyList(String name, int imageId)
    {
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
