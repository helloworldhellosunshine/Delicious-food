package com.example.food.cookbook.beans;

import java.util.List;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class TagCategoryList {

    List<CookBook> data;

    public TagCategoryList() {

    }

    public TagCategoryList(List<CookBook> data) {
        this.data = data;
    }

    public List<CookBook> getData() {
        return data;
    }

    public void setData(List<CookBook> data) {
        this.data = data;
    }


}
