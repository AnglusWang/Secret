package com.angluswang.secret.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.angluswang.secret.entity.Config;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = Config.getCachedToken(this);
        if (token != null) {
            Intent intent = new Intent(this, TimelineActivity.class);
            intent.putExtra(Config.KEY_TOKEN, token);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
