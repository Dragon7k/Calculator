package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText mailname;
    EditText password;
    EditText secondpassword;

    CustomDialogFragment dialog = new CustomDialogFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mailname=(EditText) findViewById(R.id.editTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextPassword);
        secondpassword=(EditText) findViewById(R.id.editTextSecondPassword);
    }


    public void signUp(View view) {

        String patternPass = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        String patternMail = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
        Intent intent = new Intent(this,enterActivity.class);
        String mail= String.valueOf(mailname.getText());
        String pass= String.valueOf(password.getText());
        String secPass= String.valueOf(secondpassword.getText());
        if(mail.matches(patternMail)){
            if(pass.matches(patternPass)){
                    if(pass.equals(secPass)){
                        intent.putExtra("mail",mail);
                        intent.putExtra("pass",pass);
                        startActivity(intent);
                    }else {
                        dialog.title="Пароли не совпадают";
                        dialog.show(getSupportFragmentManager(), "custom");
                    }

            }else{
                dialog.title="Пароль не должен быть меньше 8 "+ "\n" + "символов и должен содержать заглавную букву,"+"\n"+" цифры и специальные символы";
                dialog.show(getSupportFragmentManager(),"e");
            }
        }else{
            dialog.title="Адрес не совпадает со стандартным шаблоном";
            dialog.show(getSupportFragmentManager(),"u");
        }


    }
}