package com.augtons.tjuttestc1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class OutPutActivity extends AppCompatActivity {
//    private List<CardAdapter.Card> list = new ArrayList<>();
//    private int significantFigue = 2;
//    AlertDialog alertDialog;
//    CardAdapter.Card card;
//
//    View view;
//    TextInputEditText textInputEditText;
//    Context context;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_put);
//        context = this;
//        Toolbar toolbar = findViewById(R.id.toolbar2);
//        toolbar.setNavigationOnClickListener(v -> finish());
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        list.clear();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        TestC9 testC9 = getIntent().getParcelableExtra("testc9");
//        card = new CardAdapter.Card("结果（2位有效数字）", testC9.getString(2) +"\n(点击切换有效数字位数)", true);
//        card.setOnClickListener(() -> {
//            view = View.inflate(context, R.layout.alert_significant_figue, null);
//            textInputEditText = view.findViewById(R.id.alert_text_input);
//            textInputEditText.setText(String.valueOf(significantFigue));
//            textInputEditText.setText(String.valueOf(significantFigue));
//            builder.setView(view);
//            alertDialog = builder.show();
//        });
//        //切换有效数字
//        list.add(card);
//
//        list.add(new CardAdapter.Card("D₃₀和(D₃₀)²", MyMath.superRoundString(Math.abs(testC9.d30[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d30[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₂₉和(D₂₉)²", MyMath.superRoundString(Math.abs(testC9.d29[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d29[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₂₈和(D₂₈)²", MyMath.superRoundString(Math.abs(testC9.d28[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d28[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₂₇和(D₂₇)²", MyMath.superRoundString(Math.abs(testC9.d27[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d27[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₂₆和(D₂₆)²", MyMath.superRoundString(Math.abs(testC9.d26[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d26[1], 4) +" mm²", false));
//
//        list.add(new CardAdapter.Card("D₂₀和(D₂₀)²", MyMath.superRoundString(Math.abs(testC9.d20[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d20[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₁₉和(D₁₉)²", MyMath.superRoundString(Math.abs(testC9.d19[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d19[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₁₈和(D₁₈)²", MyMath.superRoundString(Math.abs(testC9.d18[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d18[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₁₇和(D₁₇)²", MyMath.superRoundString(Math.abs(testC9.d17[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d17[1], 4) +" mm²", false));
//        list.add(new CardAdapter.Card("D₁₆和(D₁₆)²", MyMath.superRoundString(Math.abs(testC9.d16[0]), 4) +" mm\n" + MyMath.superRoundString(testC9.d16[1], 4) +" mm²", false));
//
//        list.add(new CardAdapter.Card("(D₃₀)² - (D₂₀)²", MyMath.superRoundString(testC9.d30d20, 4)+" mm²", false));
//        list.add(new CardAdapter.Card("(D₂₉)² - (D₁₉)²", MyMath.superRoundString(testC9.d29d19, 4)+" mm²", false));
//        list.add(new CardAdapter.Card("(D₂₈)² - (D₁₈)²", MyMath.superRoundString(testC9.d28d18, 4)+" mm²", false));
//        list.add(new CardAdapter.Card("(D₂₇)² - (D₁₇)²", MyMath.superRoundString(testC9.d27d17, 4)+" mm²", false));
//        list.add(new CardAdapter.Card("(D₂₆)² - (D₁₆)²", MyMath.superRoundString(testC9.d26d16, 4)+" mm²", false));
//
//        list.add(new CardAdapter.Card("Dm² - Dn² (平均)",  MyMath.superRoundString(testC9.dmdn_average, 4)+ " mm²", false));
//        list.add(new CardAdapter.Card("U(Dm² - Dn²)",  MyMath.superRoundString(testC9.udmdn, 4)+ " mm²", false));
//
//        list.add(new CardAdapter.Card("R (平均)",  MyMath.superRoundString(testC9.r_average, 4)+ " mm", false));
//        list.add(new CardAdapter.Card("U(R)",  MyMath.superRoundString(testC9.u_r, 4)+ " mm", false));
//
//
//        builder.setTitle("更改有效数字位数")
//                .setView(view)
//                .setPositiveButton("确定", (dialog, which) -> {
//                    if (textInputEditText.getText() != null && !textInputEditText.getText().toString().equals("")) {
//                        significantFigue = Integer.parseInt(textInputEditText.getText().toString());
//                    }else {
//                        Toast toast = Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                        return;
//                    }
//                    recyclerView.setAdapter(new CardAdapter(list));
//                    list.get(0).itemName = "结果（"+ significantFigue +"位有效数字）";
//                    list.get(0).itemValue = testC9.getString(significantFigue) +"\n(点击切换有效数字位数)";
//                    Toast.makeText(this, "设置成功，有效数字位数为 " + significantFigue, Toast.LENGTH_SHORT).show();
//
//                }).setNegativeButton("取消", (dialog, which) -> {
//            Toast.makeText(this, "取消设置", Toast.LENGTH_SHORT).show();
//        });
//        recyclerView.setAdapter(new CardAdapter(list));
//
    }
}