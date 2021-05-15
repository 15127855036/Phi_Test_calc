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
    private List<CardAdapter.Card> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_put);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(v -> finish());
//
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        list.clear();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
        TestC1 testC1 = getIntent().getParcelableExtra("testC1");
        list.add(new CardAdapter.Card("最终结果表达式", "Y = " + testC1.getString(1), true));

        String stringY = String.valueOf(testC1.captal_Y);
        String[] stringsY;
        if(stringY.contains("E")){
            stringsY = stringY.split("E");
            stringsY[0] = stringsY[0].substring(0, 4);
            list.add(new CardAdapter.Card("Y平均值", stringsY[0] + "×10"+MyMath.getSmallNum(Integer.parseInt(stringsY[1])), true));
        }else{
            list.add(new CardAdapter.Card("Y平均值", MyMath.superRoundString(testC1.captal_Y, 3), true));
        }

        stringY = String.valueOf(testC1.uY);
        if(stringY.contains("E")){
            stringsY = stringY.split("E");
            stringsY[0] = stringsY[0].substring(0, 4);
            list.add(new CardAdapter.Card("u(Y)", stringsY[0] + "×10"+MyMath.getSmallNum(Integer.parseInt(stringsY[1])), true));
        }else{
            list.add(new CardAdapter.Card("u(Y)", MyMath.superRoundString(testC1.uY, 3), true));
        }

        list.add(new CardAdapter.Card("nᵢ平均值", "M=0：" + MyMath.superRoundString(Math.abs(testC1.m0_average), 3), true));
        list.add(new CardAdapter.Card("nᵢ平均值", "M=2kg：" + MyMath.superRoundString(Math.abs(testC1.m2_average), 3), true));
        list.add(new CardAdapter.Card("nᵢ平均值", "M=4kg：" + MyMath.superRoundString(Math.abs(testC1.m4_average), 3), true));
        list.add(new CardAdapter.Card("nᵢ平均值", "M=6kg：" + MyMath.superRoundString(Math.abs(testC1.m6_average), 3), true));
        list.add(new CardAdapter.Card("nᵢ平均值", "M=8kg：" + MyMath.superRoundString(Math.abs(testC1.m8_average), 3), true));
        list.add(new CardAdapter.Card("nᵢ平均值", "M=10kg：" + MyMath.superRoundString(Math.abs(testC1.m10_average), 3), true));

        list.add(new CardAdapter.Card("直径D平均值(米)", MyMath.superRound(testC1.average_D * 1e3, 3) + " m", true));

        list.add(new CardAdapter.Card("Δn值", MyMath.superRoundString(Math.abs(testC1.delta_n), 6), false));
        list.add(new CardAdapter.Card("F值", "58.8 N", false));
        list.add(new CardAdapter.Card("u(Δn)值", MyMath.replaceE(Math.abs(testC1.u_delta_n), 3), false));
        list.add(new CardAdapter.Card("u(D)值", MyMath.replaceE(Math.abs(testC1.uD), 3), false));

        list.add(new CardAdapter.Card("u(L)、u(x)和u(b)值", "0.0005", false));
        recyclerView.setAdapter(new CardAdapter(list));
    }
}