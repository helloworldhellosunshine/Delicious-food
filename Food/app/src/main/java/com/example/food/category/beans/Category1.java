package com.example.food.category.beans;

import java.util.List;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class Category1 {
    String name;
    List<Category2> list;
    String parentId;

    public Category1() {

    }

    public Category1(String name, List<Category2> list, String parentId) {
        this.name = name;
        this.list = list;
        this.parentId = parentId;
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

    public List<Category2> getList() {
        return list;
    }

    public void setList(List<Category2> list) {
        this.list = list;
    }


}
