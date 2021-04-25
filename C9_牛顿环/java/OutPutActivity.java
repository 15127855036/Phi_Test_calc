package com.augtons.tjuttestc9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        Intent intent = getIntent();
        list.clear();

        TestC9 testC9 = (TestC9)getIntent().getParcelableExtra("testc9");
        //todo 切换有效数字
        list.add(new CardAdapter.Card("结果（2位有效数字）", testC9.getString(2), true));

        list.add(new CardAdapter.Card("D₃₀和(D₃₀)²", testC9.d30[0] +" mm\n" + testC9.d30[1] +" mm²", true));
        list.add(new CardAdapter.Card("D₂₉和(D₂₉)²", testC9.d29[0] +" mm\n" + testC9.d29[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₂₈和(D₂₈)²", testC9.d28[0] +" mm\n" + testC9.d28[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₂₇和(D₂₇)²", testC9.d27[0] +" mm\n" + testC9.d27[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₂₆和(D₂₆)²", testC9.d26[0] +" mm\n" + testC9.d26[1] +" mm²", false));

        list.add(new CardAdapter.Card("D₂₀和(D₂₀)²", testC9.d20[0] +" mm\n" + testC9.d20[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₁₉和(D₁₉)²", testC9.d20[0] +" mm\n" + testC9.d20[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₁₈和(D₁₈)²", testC9.d20[0] +" mm\n" + testC9.d20[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₁₇和(D₁₇)²", testC9.d20[0] +" mm\n" + testC9.d20[1] +" mm²", false));
        list.add(new CardAdapter.Card("D₁₆和(D₁₆)²", testC9.d20[0] +" mm\n" + testC9.d20[1] +" mm²", false));
        
        list.add(new CardAdapter.Card("(D₃₀)² - (D₂₀)²", testC9.d30d20+" mm²", false));
        list.add(new CardAdapter.Card("(D₂₉)² - (D₁₉)²", testC9.d29d19+" mm²", false));
        list.add(new CardAdapter.Card("(D₂₈)² - (D₁₈)²", testC9.d28d18+" mm²", false));
        list.add(new CardAdapter.Card("(D₂₇)² - (D₁₇)²", testC9.d27d17+" mm²", false));
        list.add(new CardAdapter.Card("(D₂₆)² - (D₁₆)²", testC9.d26d16+" mm²", false));

        list.add(new CardAdapter.Card("Dm² - Dn² (平均)",  testC9.dmdn_average+ " mm²", false));
        list.add(new CardAdapter.Card("R (平均)",  testC9.r_average+ " mm²", false));
        list.add(new CardAdapter.Card("U(Dm² - Dn²)",  testC9.udmdn+ " mm²", false));
        list.add(new CardAdapter.Card("U(R)",  testC9.u_r+ " mm²", false));

        recyclerView.setAdapter(new CardAdapter(list));

    }
}