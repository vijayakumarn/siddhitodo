package com.siddhilabs.todo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by vijaykumarn on 07-May-15.
 */
public class TodoPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
