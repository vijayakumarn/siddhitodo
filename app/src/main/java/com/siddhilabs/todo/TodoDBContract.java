package com.siddhilabs.todo;

import android.provider.BaseColumns;

/**
 * Created by vijaykumarn on 06-May-15.
 */
public final class TodoDBContract {
    //prevent instantiation
    public TodoDBContract(){}

    /* todotable definition */
    public static abstract class TodoTable implements BaseColumns{
        public static final String TABLE_NAME="todo";
        public static final String COLUMN_NAME_TODO_TEXT="todotext";
        public static final String COLUMN_NAME_IS_TODO_COMPLETE="istodocomplete";
    }
}
