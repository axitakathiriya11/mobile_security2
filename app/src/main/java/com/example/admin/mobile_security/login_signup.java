package com.example.admin.mobile_security;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login_signup extends AppCompatActivity {
    Button btnlogin,btnsign,btn;
    dbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnsign=(Button)findViewById(R.id.btnsignup);
        db=new dbHelper(getApplication());

        btn = (Button) findViewById(R.id.btnlogout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedlogin sharedlogin = new sharedlogin();
                // mainActivity.Logout();
                SharedPreferences sharedPreferences = getSharedPreferences(sharedlogin.My_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit().clear();
                editor.commit();
                Intent i = new Intent(login_signup.this,sharedlogin.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void login(View view) {
        LayoutInflater  inflater=getLayoutInflater();
        View alertLayout=inflater.inflate(R.layout.custom_dialog,null);
        final EditText etUsername = alertLayout.findViewById(R.id.et_pin);

        final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Info");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = etUsername.getText().toString();

                Toast.makeText(getBaseContext(), "PIN: " + user, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    public void signup(View view) {
        LayoutInflater  inflater=getLayoutInflater();
        View alertLayout=inflater.inflate(R.layout.custom_dialog_sign,null);
        final EditText etphone = alertLayout.findViewById(R.id.et_phone);
        final EditText etpin = alertLayout.findViewById(R.id.et_pin);
        final EditText etcpin = alertLayout.findViewById(R.id.et_c_pin);
        final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Info");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = etphone.getText().toString();
                String pass = etpin.getText().toString();
                String cpass = etcpin.getText().toString();
                db.open();
                if(pass.equals(cpass)) {
                    db.insert(user, pass);
                }
                Toast.makeText(getBaseContext(), "Phone Number: " + user + " PIN: " + pass +"Conform-PIN:" + cpass, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }
public void location(View view)
{
    Intent i = new Intent(login_signup.this,MainActivity.class);
    startActivity(i);
}
}
