package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    private static double memory=0;
    private static boolean isOne=false;
    EditText numberField;   // поле для ввода числа
    String forCalculate ="";
    Calculator calculator = new Calculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        numberField = (EditText) findViewById(R.id.resultField);
    }

    public void OnClearClick(View view){
        numberField.setText("");
        forCalculate="";
    }

    public void MRC(View view){
        Button button = (Button)view;
        String s = String.valueOf(button.getText());

        switch (s){
            case "M+":
                memory+=Double.parseDouble(String.valueOf(numberField.getText()));
                break;
            case "M-":
                memory -= Double.parseDouble(String.valueOf(numberField.getText()));
                break;
            case "MR":
                String l = String.valueOf(numberField.getText());
                numberField.setText(l+Double.toString(memory));
                forCalculate+=Double.toString(memory)+" ";

                break;
            case "MC":
                memory=0;
                break;
        }

    }

    // обработка нажатия
    public void onSetstringClick(View view) {

        boolean flag = false;
        Button button = (Button) view;
        numberField.append(button.getText());

        if (button.getText().equals("+")
                || button.getText().equals("-")
                || button.getText().equals("*")
                || button.getText().equals("/"))
        {
            forCalculate += " " + button.getText() + " ";
            flag = true;
        } else if (button.getText().equals("cos")
                || button.getText().equals("sin")
                || button.getText().equals("sqrt")
                ||button.getText().equals("("))
        {
            forCalculate += button.getText() + " ";
            flag = true;
        }
        else if (button.getText().equals(")")) {
            forCalculate += " " + button.getText();
            flag=true;
        }

        if(flag==false||button.getText().equals('.')){
            forCalculate+=button.getText();
        }


    }


    private String prepareString(String forFinal){
        String finalString = new String();
        for(int token=0;token<forFinal.length();token++){
            char simbol = forFinal.charAt(token);
            if(simbol=='-'){
                if(forFinal.charAt(token-3)=='('){
                    finalString+="0 ";
                }
            }
            finalString+=simbol;
        }

        System.out.println("its finaly string"+finalString);
        return finalString;
    }
    

    public void resultClick(View view){
        System.out.println(forCalculate);
        String[] str;
        String sort= new String();

        String finalStr = prepareString(forCalculate);
        System.out.println("final:" +finalStr );
        if(finalStr.startsWith(" ")){
            str = finalStr.split(" ");

        }else
        {
           sort = "0 + "+finalStr;
            str = sort.split(" ");
        }


        List<String> tokens = new ArrayList<String>(Arrays.asList(str));
        tokens.remove("");
        ListIterator<String> listIterator = tokens.listIterator();

        System.out.println(tokens);
        System.out.println(" 0 elements: " +tokens.get(0));

        double Answer = calculator.calculate(listIterator);
        numberField.setText(Double.toString(Answer));
        System.out.println(Double.toString(Answer));
        forCalculate=Double.toString(Answer);

    }




}