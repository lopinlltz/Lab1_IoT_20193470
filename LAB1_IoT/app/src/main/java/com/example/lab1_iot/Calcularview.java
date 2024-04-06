package com.example.lab1_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calcularview extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewScreen;
    private TextView textViewOperation;
    private TextView textViewResult;
    private StringBuilder currentNumber;
    private char operator;
    private double operand1;
    private double operand2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcular_main);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calcularview.this, MainActivity.class);
                startActivity(intent);
            }
        });

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

        if (isValidExpression(currentNumber.toString())) {
            int operatorIndex = getOperatorIndex();

            if (operatorIndex != -1) {
                String operand1 = currentNumber.substring(0, operatorIndex);
                char operator = currentNumber.charAt(operatorIndex);
                String operand2 = currentNumber.substring(operatorIndex + 1);
                double op1 = Double.parseDouble(operand1);
                double op2 = Double.parseDouble(operand2);

                switch (operator) {
                    case '+':
                        textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                        textViewResult.setText(String.valueOf(op1 + op2));
                        break;
                    case '-':
                        textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                        textViewResult.setText(String.valueOf(op1 - op2));
                        break;
                    case '*':
                        textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                        textViewResult.setText(String.valueOf(op1 * op2));
                        break;
                    case '/':
                        if (op2 == 0) {
                            textViewOperation.setText("No se puede dividir entre 0");
                            textViewResult.setText("Error");
                        } else {
                            textViewOperation.setText(String.format("%s %s %s", operand1, operator, operand2));
                            textViewResult.setText(String.valueOf(op1 / op2));
                        }
                        break;
                    default:
                        break;
                }
                currentNumber.setLength(0);
                this.operand1 = 0;
                this.operand2 = 0;
                operator = '\u0000';
            }
        } else {
            Toast.makeText(this, "no válido", Toast.LENGTH_SHORT).show();
            clearScreen();
        }
    }

    private int getOperatorIndex() {
        int operatorIndex = -1;
        if (currentNumber.indexOf("+") != -1) {
            operatorIndex = currentNumber.indexOf("+");
        } else if (currentNumber.indexOf("-") != -1) {
            operatorIndex = currentNumber.indexOf("-");
        } else if (currentNumber.indexOf("*") != -1) {
            operatorIndex = currentNumber.indexOf("*");
        } else if (currentNumber.indexOf("/") != -1) {
            operatorIndex = currentNumber.indexOf("/");
        }
        return operatorIndex;
    }

    private boolean isValidExpression(String str) {
        boolean isFirstNumber = true;

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                // Si el carácter es un dígito, está permitido en ambas posiciones
                isFirstNumber = false;
            } else if (c == '.' && isFirstNumber) {
                // Si el carácter es un punto decimal y es el primer número, está permitido
                isFirstNumber = false;
            } else if (isOperator(c) && !isFirstNumber) {
                // Si el carácter es un operador y no es el primer número, está permitido
                isFirstNumber = true;  // Después de un operador, el siguiente debe ser un número
            } else {
                // Si el carácter no es un dígito, un punto decimal o un operador, la expresión no es válida
                return false;
            }
        }

        // Si se ha recorrido toda la cadena sin encontrar problemas, la expresión es válida
        return true;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' ||c=='/';
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
