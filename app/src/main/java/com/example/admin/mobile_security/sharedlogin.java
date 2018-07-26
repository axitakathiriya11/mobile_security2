package com.example.admin.mobile_security;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class sharedlogin extends AppCompatActivity {
    public Button btn;
    EditText num,pass;

    public SharedPreferences sharedPreferences;

    public static final String My_PREF = "mypref";
    public static final String NUMBER = "num";
    public static final String PASS = "pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedlogin);
        btn = (Button) findViewById(R.id.btnlogin);
        num = (EditText)findViewById(R.id.et_phone);
        pass = (EditText)findViewById(R.id.et_pin);

        sharedPreferences = getSharedPreferences(My_PREF, Context.MODE_PRIVATE);

        checkLogin();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(NUMBER,num.getText().toString());
                editor.putString(PASS,pass.getText().toString());
                editor.commit();
                Intent i = new Intent(sharedlogin.this,login_signup.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void checkLogin() {
        if(sharedPreferences.contains(NUMBER) && sharedPreferences.contains(PASS)) {
            String num=sharedPreferences.getString(NUMBER,"");
            String pass =sharedPreferences.getString(PASS,"");
            if(!num.equals(null) && !pass.equals(null)) {
                Intent i = new Intent(sharedlogin.this,login_signup.class);
                startActivity(i);
                finish();
            }
        }
    }
    public void Logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.commit();
    }
}
