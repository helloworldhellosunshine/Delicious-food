package com.example.food.category.beans;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class Category2 {

    String parentId;
    String name;
    String id;


    public Category2() {

    }

    public Category2(String parentId, String name, String id) {
        this.parentId = parentId;
        this.name = name;
        this.id = id;
    }


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
