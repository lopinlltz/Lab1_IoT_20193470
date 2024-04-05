package com.example.lab1_iot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calcularview extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewScreen;
    private TextView textViewOperation;
    private TextView textViewResult;
    private StringBuilder currentNumber;
    private double operand1;
    private double operand2;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcular_main);

        textViewScreen = findViewById(R.id.textViewScreen);
        textViewOperation = findViewById(R.id.textViewOperation);
        textViewResult = findViewById(R.id.textViewResult);

        currentNumber = new StringBuilder();

        setClickListeners();
    }

    private void setClickListeners() {
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonPlus).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);
        findViewById(R.id.buttonFor).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);

        findViewById(R.id.buttonCLR).setOnClickListener(this);
        findViewById(R.id.buttonEqual).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        if (v.getId() == R.id.buttonCLR) {
            clearScreen();
        }
        else if (v.getId() == R.id.buttonEqual) {
            calculateResult();
        }

        else {
            appendToScreen(buttonText);
        }
    }

    private void appendToScreen(String text) {
        currentNumber.append(text);
        textViewScreen.setText(currentNumber.toString());
    }

    private void clearScreen() {
        currentNumber.setLength(0);
        textViewScreen.setText("");
    }

    private void calculateResult() {

        if (isValidNumber(currentNumber.toString())) {

            double current = Double.parseDouble(currentNumber.toString());
            switch (operator) {
                case '+':
                    operand2 = current;
                    textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                    textViewResult.setText(String.valueOf(operand1 + operand2));
                    break;
                case '-':
                    operand2 = current;
                    textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                    textViewResult.setText(String.valueOf(operand1 - operand2));
                    break;
                case '*':
                    operand2 = current;
                    textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                    textViewResult.setText(String.valueOf(operand1 * operand2));
                    break;
                case '/':
                    operand2 = current;
                    if (operand2 == 0) {
                        textViewOperation.setText("No se puede dividir entre 0");
                        textViewResult.setText("Error");
                    } else {
                        textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                        textViewResult.setText(String.valueOf(operand1 / operand2));
                    }
                    break;
                default:
                    break;
            }
            currentNumber.setLength(0);
            operand1 = 0;
            operand2 = 0;
            operator = '\u0000';
        } else {
            Toast.makeText(this, "no válido", Toast.LENGTH_SHORT).show();
            clearScreen();
        }
    }

    private boolean isValidNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }

}
