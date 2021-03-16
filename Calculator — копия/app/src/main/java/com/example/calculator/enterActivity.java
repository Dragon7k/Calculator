package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class enterActivity extends AppCompatActivity {

    EditText mailname;
    EditText password;
    public final String name="lox@mail.ru";
    public final String Pass ="lox2001";
    CustomDialogFragment dialog = new CustomDialogFragment();
    private  HashMap<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        mailname=(EditText) findViewById(R.id.editTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextPassword);
        users.put(name,Pass);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("okno entera na pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String login = null;
        String pass = null;
        System.out.println("okno entera vozobnovleno");
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            login = arguments.get("mail").toString();
            pass = arguments.get("pass").toString();
        }
        users.put(login, pass);
        System.out.println(users);
    }

    public void signIn(View view) {
          Intent intent = new Intent(enterActivity.this, MainActivity.class);
          System.out.println("zashli syuda");
          String a = String.valueOf(mailname.getText());
          String b = String.valueOf(password.getText());


           if(users.containsKey(a)){
            if(users.get(a).equals(b)) {
                System.out.println("all ok");
                startActivity(intent);
            }else{
                dialog.title="неверный логин или пароль";
                dialog.show(getSupportFragmentManager(), "t");
            }
        }else{

            dialog.title="неверный логин или пароль";
            dialog.show(getSupportFragmentManager(), "r");
        }


        
    }

    public void signUp(View view) {

        Intent intent = new Intent(enterActivity.this, SignUp.class);
        try {

            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}