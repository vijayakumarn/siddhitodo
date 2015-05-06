package com.siddhilabs.todo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class TodoMain extends AppCompatActivity {

    ListView todoList;
    static ArrayList<String> models = new ArrayList<String>();
    CustomListAdapter listAdapter = null;
    static SQLiteDatabase appDb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);

        //Get the list view from layout
        todoList = (ListView) findViewById(R.id.listView);
        appDb = getAppDb(getApplicationContext());
        buildList();
        listAdapter = new CustomListAdapter(this, models);
        todoList.setAdapter(listAdapter);

        Button addButton = (Button) findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
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
                persistTodo(inputText);
                models.add(inputText);
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


    static void buildList(){
        models.clear();
        String [] projection = {TodoDBContract.TodoTable.COLUMN_NAME_TODO_TEXT};
        Cursor c = appDb.query(
                TodoDBContract.TodoTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            models.add(c.getString(c.getColumnIndex(TodoDBContract.TodoTable.COLUMN_NAME_TODO_TEXT)));
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

    SQLiteDatabase getAppDb(Context context){
        TodoDBHelper dbHelper = new TodoDBHelper(context);
        return dbHelper.getWritableDatabase();
    }

    void persistTodo(String todoText){
        ContentValues values = new ContentValues();
        values.put(TodoDBContract.TodoTable.COLUMN_NAME_TODO_TEXT, todoText);
        appDb.insert(TodoDBContract.TodoTable.TABLE_NAME, null, values);
        //:Todo Toast here
    }

    static void removeTodo(String todoText){
        String selection = TodoDBContract.TodoTable.COLUMN_NAME_TODO_TEXT + " LIKE ?";
        String[] selectionArgs = {todoText};
        appDb.delete(TodoDBContract.TodoTable.TABLE_NAME, selection, selectionArgs);
    }
}
