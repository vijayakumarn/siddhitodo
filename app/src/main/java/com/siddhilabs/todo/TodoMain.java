package com.siddhilabs.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class TodoMain extends AppCompatActivity {

    ListView todoList;
    static ArrayList<ListItemModel> models = buildList();
    CustomListAdapter listAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);

        //Get the list view from layout
        todoList = (ListView) findViewById(R.id.listView);
        listAdapter = new CustomListAdapter(this, models);
        todoList.setAdapter(listAdapter);

        Button addButton = (Button) findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showAddTodoDialog();
            }
        });

    }

    private void showAddTodoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_todo_dialog_title);

        //inflate the prompt layout
        LayoutInflater li = LayoutInflater.from(this);
        View promptView = li.inflate(R.layout.enter_todo, null);
        final EditText editText = (EditText) promptView.findViewById(R.id.editText2);

        builder.setView(promptView);


        builder.setPositiveButton(R.string.button_text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //save the todo
                String inputText = editText.getText().toString();
                ListItemModel newtodo = new ListItemModel(inputText, 0);
                TodoMain.addTodoList(newtodo);
                listAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.button_text_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do cancel operation.
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    static ArrayList<ListItemModel> buildList(){
        //list values
        models = new ArrayList<ListItemModel>();
        /*models.add(new ListItemModel("Bank transactions", 0));
        models.add(new ListItemModel("Trading !", 1));
        models.add(new ListItemModel("flipkart shopping", 1));
        models.add(new ListItemModel("myntra check", 0));
        models.add(new ListItemModel("snapdeal deal of the day", 0));*/


        return models;
    }

    static void addTodoList(ListItemModel model){
        if(models !=  null){
            models.add(model);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
