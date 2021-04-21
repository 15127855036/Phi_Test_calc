package com.augtons.tjuttestc8;

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
        list.add(new CardAdapter.Card("最终结果表达式", intent.getStringExtra("final") + " nm", true));
        list.add(new CardAdapter.Card("(平均)λ", intent.getStringExtra("lambda_average"), true));
        list.add(new CardAdapter.Card("Uλ", intent.getStringExtra("u_lambda"), true));
        list.add(new CardAdapter.Card("Δd1", intent.getStringExtra("delta_d1"), false));
        list.add(new CardAdapter.Card("Δd2", intent.getStringExtra("delta_d2"), false));
        list.add(new CardAdapter.Card("Δd3", intent.getStringExtra("delta_d3"), false));
        list.add(new CardAdapter.Card("Δd4", intent.getStringExtra("delta_d4"), false));
        list.add(new CardAdapter.Card("Δd5", intent.getStringExtra("delta_d5"), false));
        list.add(new CardAdapter.Card("(平均)Δd", intent.getStringExtra("delta_d_average"), false));
        list.add(new CardAdapter.Card("Ua", intent.getStringExtra("ua"), false));
        list.add(new CardAdapter.Card("Ub", intent.getStringExtra("ub"), false));
        list.add(new CardAdapter.Card("Ud", intent.getStringExtra("u_d"), false));
        

        recyclerView.setAdapter(new CardAdapter(list));

    }
}