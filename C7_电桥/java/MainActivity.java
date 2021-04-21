package com.augtons.dianqiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText inputR1;
    TextInputEditText inputR2;
    TextInputEditText inputR0;
    TextInputEditText inputDeltaR0;
    TextInputEditText inputDeltaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputR1 = findViewById(R.id.input_r1);
        inputR2 = findViewById(R.id.input_r2);
        inputR0 = findViewById(R.id.input_r0);

        inputDeltaR0 = findViewById(R.id.input_delta_r0);
        inputDeltaN = findViewById(R.id.input_delta_n);
        Button btnClean = findViewById(R.id.button_clear);
        btnClean.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                inputR1.setText("100");
                inputR2.setText("100");
                inputR0.setText("199.1");
                inputDeltaN.setText("2.25");
                inputDeltaR0.setText("0.2");
                return true;
            }
        });
        Button button = findViewById(R.id.button_run);
        button.setOnClickListener(v -> {
            String string_r1 = inputR1.getText().toString();
            String string_r2 = inputR2.getText().toString();
            String string_r0 = inputR0.getText().toString();
            String string_delta_r0 = inputDeltaR0.getText().toString();
            String string_delta_n = inputDeltaN.getText().toString();
            boolean canRun = true;
            double r1 = 0;
            if (!string_r1.equals("") && !string_r1.equals(".")) {
                r1 = Double.parseDouble(string_r1);
            }else {
                canRun = false;
            }
            
            double r2 = 0;
            if (!string_r2.equals("") && !string_r2.equals(".")) {
                r2 = Double.parseDouble(string_r2);
            }else {
                canRun = false;
            }

            double r0 = 0;
            if (!string_r0.equals("") && !string_r0.equals(".")) {
                r0 = Double.parseDouble(string_r0);
            }else {
                canRun = false;
            }

            double delta_r0 = 0;
            if (!string_delta_r0.equals("") && !string_delta_r0.equals(".")){
                delta_r0 = Double.parseDouble(string_delta_r0);
            }else {
                canRun = false;
            }
            double delta_n = 0;
            if (!string_delta_n.equals("") && !string_delta_n.equals(".")){
                delta_n = Double.parseDouble(string_delta_n);
            }else {
                canRun = false;
            }
            if (canRun) {
                if (r1 > 99999.9 || r2 > 99999.9 || r0 > 99999.9){
                    Toast.makeText(getApplicationContext(), "R1、R2、R0的值不得大于99999.9Ω！", Toast.LENGTH_SHORT).show();
                }else {
                    GetOutPut getOutPut = new GetOutPut(r1, r2, r0, delta_r0, delta_n);
                    getOutPut.start();
                    Intent intent = new Intent(MainActivity.this, Output.class);
                    intent.putExtra("OutPut", getOutPut);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(getApplicationContext(), "输入数值有误或为空，请检查！", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void clear (View view){
        inputR1.setText("");
        inputR2.setText("");
        inputR0.setText("");
        inputDeltaN.setText("");
        inputDeltaR0.setText("");
    }

}