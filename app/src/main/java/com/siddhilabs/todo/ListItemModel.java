package com.siddhilabs.todo;

/**
 * Created by vijaykumarn on 01-May-15.
 */
public class ListItemModel {

    String text;
    int checkValue;

    public ListItemModel(String text, int checkValue) {
        this.text = text;
        this.checkValue = checkValue;
    }

    public int getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(int checkValue) {
        this.checkValue = checkValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
