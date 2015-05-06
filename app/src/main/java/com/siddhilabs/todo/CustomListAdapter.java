package com.siddhilabs.todo;

import android.app.Activity;
import android.content.Context;
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
public class CustomListAdapter extends ArrayAdapter<ListItemModel>{

    ArrayList<ListItemModel> listItems;
    Context context;

    public CustomListAdapter(Context context, ArrayList<ListItemModel> objects) {
        super(context, R.layout.todo_item_row_layout, objects);
        this.context = context;
        this.listItems = objects;
    }

    @Override
    public int getCount() {
        return TodoMain.models.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.todo_item_row_layout, parent, false);

        TextView editText = (TextView)convertView.findViewById(R.id.editText);
        editText.setText(listItems.get(position).getText());

        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
        if(listItems.get(position).getCheckValue() == 1){
            cb.setChecked(true);
        }
        else{
            cb.setChecked(false);
        }

        //click event listener for checkbox
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    listItems.remove(position);
                    TodoMain.models = listItems;
                    notifyDataSetChanged();
                    //notifyDataSetInvalidated();
                }
            }
        });

        return convertView;
    }


}
