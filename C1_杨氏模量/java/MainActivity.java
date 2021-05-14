package com.augtons.tjuttestc1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Handler handler = new Handler();
    private List<MainAdapter.Edit> list = new ArrayList<>();

    //todo 测试数据
    private final double[] testValue2 = {
            0.722, 0.696, 0.700, 0.690, 0.690, 0.731,
            0.5898, 0.8115, 0.07727
    };
    private final double[][] testValue1 = {
            {6.58, 6.82, 7.12, 7.55, 7.88, 8.29},
            {6.49, 6.81, 7.20, 7.53, 7.90, 8.29}
    };
    private Button buttonRun;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRun = findViewById(R.id.button_run);
        buttonClear = findViewById(R.id.button_clear);

        recyclerView = findViewById(R.id.recycler1);

        reloadInputList();

        buttonClear.setOnLongClickListener(v -> {
            reloadInputList();
            for (int i = 0; i<6; i++){
                MainAdapter.Edit edit = list.get(i);
                if (edit.textInputEditText1 != null){
                    edit.textInputEditText1.setText(String.valueOf(testValue1[0][i]));
                }
                if (edit.textInputEditText2 != null){
                    edit.textInputEditText2.setText(String.valueOf(testValue1[1][i]));
                }
                edit.value1 = String.valueOf(testValue1[0][i]);
                edit.value2 = String.valueOf(testValue1[1][i]);
            }
            for (int i = 0; i<9; i++){
                MainAdapter.Edit edit = list.get(i+6);
                if (edit.textInputEditText1 != null){
                    edit.textInputEditText1.setText(String.valueOf(testValue2[i]));
                }
                edit.value1 = String.valueOf(testValue2[i]);
            }
            return true;
        });

        buttonClear.setOnClickListener( v -> {
            for (int i = 0; i<6; i++){
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
            for (int i = 0; i<9; i++){
                MainAdapter.Edit edit = list.get(i+6);
                if (edit.textInputEditText1 != null){
                    edit.textInputEditText1.setText("");
                }
                edit.value1 = "";
            }
        });
    }

    public void startRun(View view){
        //todo 初始化 TestC1 类，传递数据到OutPutActivity
        double[][] ms = new double[6][2];

        double[] ds = new double[6];
        double captal_L = 0;
        double x = 0;
        double b = 0;
        boolean canRun = true;

        for (int i = 0; i<6;i++){
            MainAdapter.Edit edit = list.get(i);
            String value1 = edit.value1;
            String value2 = edit.value2;
            if (!value1.equals("") && !value1.equals(".")){
                ms[i][0] = Double.parseDouble(value1);
            }else {
                canRun = false;
                break;
            }
            if (!value2.equals("") && !value2.equals(".")){
                ms[i][1] = Double.parseDouble(value2);
            }else {
                canRun = false;
                Toast.makeText(this, "请检查输入", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        for(int i = 0; i<6; i++){
            MainAdapter.Edit edit = list.get(i+6);
            String value1 = edit.value1;
            if (!value1.equals("") && !value1.equals(".")){
                ds[i] = Double.parseDouble(value1);
            }else {
                canRun = false;
                break;
            }
        }

        String value_L = list.get(12).value1;
        String value_x = list.get(13).value1;
        String value_b = list.get(14).value1;
        if(!value_L.equals("") && !value_L.equals(".")){
            captal_L = Double.parseDouble(value_L);
        }else{
            canRun = false;
        }

        if (!value_x.equals("") && !value_x.equals(".")) {
            x = Double.parseDouble(value_x);
        }else{
            canRun = false;
        }

        if (!value_b.equals("") && !value_b.equals(".")) {
            b = Double.parseDouble(value_b);
        }else{
            canRun = false;
        }

        if (canRun){
            TestC1 testC1 = new TestC1(ms[0], ms[1], ms[2], ms[3], ms[4], ms[5], ds, captal_L, x, b);
            testC1.start();

            Intent intent = new Intent(this, OutPutActivity.class);
            intent.putExtra("testC1", testC1);
            startActivity(intent);
        }
    }
    public void reloadInputList(){
        list.clear();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        list.add(new MainAdapter.Edit("0kg", MainAdapter.EditMode.DOUBLE));
        list.add(new MainAdapter.Edit("2kg", MainAdapter.EditMode.DOUBLE));
        list.add(new MainAdapter.Edit("4kg", MainAdapter.EditMode.DOUBLE));
        list.add(new MainAdapter.Edit("6kg", MainAdapter.EditMode.DOUBLE));
        list.add(new MainAdapter.Edit("8kg", MainAdapter.EditMode.DOUBLE));
        list.add(new MainAdapter.Edit("10kg", MainAdapter.EditMode.DOUBLE));

        list.add((new MainAdapter.Edit("D测量第1次", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("D测量第2次", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("D测量第3次", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("D测量第4次", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("D测量第5次", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("D测量第6次", MainAdapter.EditMode.ONE)));

        list.add((new MainAdapter.Edit("L", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("x", MainAdapter.EditMode.ONE)));
        list.add((new MainAdapter.Edit("b", MainAdapter.EditMode.ONE)));


        recyclerView.setAdapter(new MainAdapter(list));
    }
}