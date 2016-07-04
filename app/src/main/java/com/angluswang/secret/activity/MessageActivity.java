package com.angluswang.secret.activity;

import android.app.ListActivity;
import android.os.Bundle;

import com.angluswang.secret.R;

public class MessageActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);
    }
}
