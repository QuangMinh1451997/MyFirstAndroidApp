package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import common.Helper;
import models.Constant;
import models.User;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText inputPhone;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) this.findViewById(R.id.button_login);

        this.inputPhone = (EditText) findViewById(R.id.input_phone);
        this.inputPassword = (EditText) findViewById(R.id.input_password);

        this.initEventClickLogin();
    }

    private void initEventClickLogin(){
        btnLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                LoginActivity ctx = (LoginActivity)v.getContext();

                String phone = ctx.inputPhone.getText().toString();
                String password = ctx.inputPassword.getText().toString();


//                User user = ctx.db.VerifyLogin(phone, password);
                User user = Constant.user;
                if(user == null){
                    AlertDialog.Builder dialogBuilder = Helper.GetDialogBuilder(ctx, "Login Failed", "Phone or password is incorrect");
                    dialogBuilder
                            .setPositiveButton("Yes", null)
                            .show();
                }
                else{
                    Intent myIntent = new Intent(ctx, MainActivity.class);
                    myIntent.putExtra("user", user);
                    myIntent.putExtra("fragment", "home");
                    // Yêu cầu chạy Example5Activity.
                    ctx.startActivity(myIntent);
                }
            }
        });
    }
}
