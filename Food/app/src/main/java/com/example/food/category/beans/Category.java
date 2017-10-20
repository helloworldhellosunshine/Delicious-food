package com.example.food.category.beans;

import com.example.food.MainActivity;

import java.util.List;

/**
 * Created by 王帝 on 2017/9/15.
 */

public class Category  {

    String resultcode;
    String reason;
    List<Category1> result;


    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Category1> getResult() {
        return result;
    }

    public void setResult(List<Category1> result) {
        this.result = result;
    }


}
