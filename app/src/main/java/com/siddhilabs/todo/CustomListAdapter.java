package com.siddhilabs.todo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vijaykumarn on 01-May-15.
 */
public class CustomListAdapter extends ArrayAdapter<TodoItemModel>{

    ArrayList<TodoItemModel> listItems;
    Context context;

    public CustomListAdapter(Context context, ArrayList<TodoItemModel> objects) {
        super(context, R.layout.todo_item_row_layout, objects);
        this.context = context;
        this.listItems = objects;
    }

    @Override
    public int getCount() {
        return TodoMain.modelList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.todo_item_row_layout, parent, false);

        final TextView editText = (TextView)convertView.findViewById(R.id.editText);
        editText.setText(listItems.get(position).getText());
        Integer completedFlag = listItems.get(position).getCheckValue();
        final boolean showCompleted = (completedFlag != null && completedFlag == 1);
        if(showCompleted){
            editText.setPaintFlags(editText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
        if(showCompleted){
            cb.setChecked(true);
        }
        //click event listener for checkbox
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //TodoMain.removeTodo(listItems.get(position));
                    //listItems.remove(position);
                    //TodoMain.models = listItems;
                    //notifyDataSetChanged();
                    //notifyDataSetInvalidated();
                    editText.setPaintFlags(editText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    TodoMain.updateIsTodoComplete(1, editText.getText().toString());

                    if(!showCompleted){
                        listItems.remove(position);
                        //TodoMain.modelList.remove(position);
                        notifyDataSetChanged();
                    }
                }
                else{
                    //un-strike through
                    editText.setPaintFlags(0);
                    TodoMain.updateIsTodoComplete(0, editText.getText().toString());
                }
            }
        });

        return convertView;
    }


}
