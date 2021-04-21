package com.augtons.tjuttestc8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<MainAdapter.EditTestTag> list = new ArrayList<>();
    private Button clearButton;
    private double[] parcel = new double[10];
    private static final String[] TEST_VALUE = {
            "37.53175",
            "37.54799",
            "37.56175",
            "37.57769",
            "37.59600",
            "37.60640",
            "37.62370",
            "37.64087",
            "37.65424",
            "37.67430",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        clearButton = findViewById(R.id.button_clear);

        list.add(new MainAdapter.EditTestTag("D1"));
        list.add(new MainAdapter.EditTestTag("D2"));
        list.add(new MainAdapter.EditTestTag("D3"));
        list.add(new MainAdapter.EditTestTag("D4"));
        list.add(new MainAdapter.EditTestTag("D5"));
        list.add(new MainAdapter.EditTestTag("D6"));
        list.add(new MainAdapter.EditTestTag("D7"));
        list.add(new MainAdapter.EditTestTag("D8"));
        list.add(new MainAdapter.EditTestTag("D9"));
        list.add(new MainAdapter.EditTestTag("D10"));

        recyclerView.setAdapter(new MainAdapter(list));

        clearButton.setOnLongClickListener(v -> {
            if (list != null && list.size() >= 10) {
                for (int i = 0; i<10; i++){
                    MainAdapter.EditTestTag testTag = list.get(i);
                    if (testTag.editText != null){
                        testTag.editText.setText(TEST_VALUE[i]);
                    }else {
                        testTag.preText = TEST_VALUE[i];
                    }
                }
            }
            return true;
        });
    }
    public void startRun(View view){
        boolean canRun = true;
        for (int i = 0; i<10; i++){
            MainAdapter.EditTestTag testTag = list.get(i);
            String val;
            if (testTag.editText != null){
                val = testTag.editText.getText().toString();
                if (!val.equals("") && !val.equals(".")) {
                    parcel[i] = Double.parseDouble(val);
                }else{
                    canRun = false;
                    break;
                }
            }else {
                val = testTag.preText;
                if (!val.equals("") && !val.equals(".")) {
                    parcel[i] = Double.parseDouble(val);
                }else {
                    canRun = false;
                    break;
                }
            }
            val = "";
        }
        //todo 判断数组是否可以下一步，否则跳出程序报错
        if (canRun) {
            TestC8 testC8 = new TestC8(
                    parcel[0],
                    parcel[1],
                    parcel[2],
                    parcel[3],
                    parcel[4],
                    parcel[5],
                    parcel[6],
                    parcel[7],
                    parcel[8],
                    parcel[9]
            );
            testC8.start();
            shouOutPut(testC8);
        }else {
            Toast.makeText(this, "请检查输入", Toast.LENGTH_SHORT).show();
        }
    }
    public void clear(View view){
        for (MainAdapter.EditTestTag editTestTag : list){
            if (editTestTag.editText != null) {
                editTestTag.editText.setText("");
            }else {
                editTestTag.preText = "";
            }
        }
    }

    private void shouOutPut(TestC8 testC8){
        Intent intent = new Intent(this, OutPutActivity.class);
        intent.putExtra("final", testC8.getString())
                .putExtra("delta_d1", String.valueOf(testC8.delta_d1))
                .putExtra("delta_d2",  String.valueOf(testC8.delta_d2))
                .putExtra("delta_d3",  String.valueOf(testC8.delta_d3))
                .putExtra("delta_d4",  String.valueOf(testC8.delta_d4))
                .putExtra("delta_d5",  String.valueOf(testC8.delta_d5))
                .putExtra("delta_d_average",  String.valueOf(testC8.delta_d_average))
                .putExtra("lambda_average",  String.valueOf(testC8.lambda_average))
                .putExtra("ua",  String.valueOf(testC8.ua))
                .putExtra("ub",  String.valueOf(testC8.ub))
                .putExtra("u_d",  String.valueOf(testC8.u_d))
                .putExtra("u_lambda",  String.valueOf(testC8.u_lambda));
        startActivity(intent);
    }
}