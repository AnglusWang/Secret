package com.angluswang.secret.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.angluswang.secret.R;
import com.angluswang.secret.net.GetCode;

/**
 * Created by Jeson on 2016/7/2.
 * 登录界面
 */

public class LoginActivity extends Activity {

    private EditText etPhoneNum;
    private Button btnGetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        initView();

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = etPhoneNum.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(LoginActivity.this, "Phone number can't be empty !",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog pd = ProgressDialog.show(LoginActivity.this,
                        "Connecting", "Please waiting ...");

                new GetCode(phone,
                        new GetCode.SuccessCallback() {
                            @Override
                            public void onSuccess(String result) {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "Success to getCode !",
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        new GetCode.FailedCallback() {
                            @Override
                            public void onFailed() {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "Failed to getCode !",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void initView() {
        etPhoneNum = (EditText) findViewById(R.id.et_phoneNum);
        btnGetCode = (Button) findViewById(R.id.btn_getCode);
    }
}
