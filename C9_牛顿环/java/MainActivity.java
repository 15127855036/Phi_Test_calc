package com.augtons.tjuttestc9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<MainAdapter.Edit> list = new ArrayList<>();

    private final String[] testValue1 = {"17.823", "17.955", "18.087", "18.209", "18.340", "19.189", "19.355", "19.508", "19.668", "19.845"};
    private final String[] testValue2 = {"32.615", "32.523", "32.430", "32.261", "32.131", "31.308", "31.150", "30.985", "30.853", "30.651"};

    private Button buttonRun;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRun = findViewById(R.id.button_run);
        buttonClear = findViewById(R.id.button_clear);

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        for (int i = 30; i >= 26;i--){
            list.add(new MainAdapter.Edit(String.valueOf(i)));
        }
        for (int i = 20; i >= 16;i--){
            list.add(new MainAdapter.Edit(String.valueOf(i)));
        }
        recyclerView.setAdapter(new MainAdapter(list));

        buttonClear.setOnLongClickListener(v -> {
            for (int i = 0; i<10; i++){
                MainAdapter.Edit edit = list.get(i);
                if (edit.textInputEditText1 != null){
                    edit.textInputEditText1.setText(testValue1[i]);
                }
                if (edit.textInputEditText2 != null){
                    edit.textInputEditText2.setText(testValue2[i]);
                }
                edit.value1 = testValue1[i];
                edit.value2 = testValue2[i];
            }
            return true;
        });

        buttonClear.setOnClickListener(v -> {
            for (int i = 0; i<10; i++){
                MainAdapter.Edit edit = list.get(i);
                if (edit.textInputEditText1 != null){
                    edit.textInputEditText1.setText("");
                }
                if (edit.textInputEditText2 != null){
                    edit.textInputEditText2.setText("");
                }
                edit.value1 = "";
                edit.value2 = "";
            }
        });
    }

    public void startRun(View view){
        double[] dl = new double[10];
        double[] dr = new double[10];
        boolean canRun = true;
        for (int i = 0; i<10;i++){
            MainAdapter.Edit edit = list.get(i);
            String value1 = edit.value1;
            String value2 = edit.value2;
            if (!value1.equals("") && !value1.equals(".")){
                dl[i] = Double.parseDouble(value1);
            }else {
                canRun = false;
                break;
            }
            if (!value2.equals("") && !value2.equals(".")){
                dr[i] = Double.parseDouble(value2);
            }else {
                canRun = false;
                Toast.makeText(this, "请检查输入", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (canRun){
            TestC9 testC9 = new TestC9(dl, dr);
            testC9.start();

            Intent intent = new Intent(MainActivity.this, OutPutActivity.class);
            intent.putExtra("testc9", testC9);
            startActivity(intent);

        }
    }
}