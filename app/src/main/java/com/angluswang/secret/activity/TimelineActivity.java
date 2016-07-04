package com.angluswang.secret.activity;

import android.app.ListActivity;
import android.os.Bundle;

import com.angluswang.secret.R;

/**
 * Created by Jeson on 2016/7/2.
 * 呈现所有聊天消息列表的界面
 */

public class TimelineActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);
    }
}
