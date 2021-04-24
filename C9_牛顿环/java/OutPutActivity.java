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
        //todo 显示结果，添加List
        //list.add(new CardAdapter.Card("最终结果表达式", intent.getStringExtra("final") + " nm", true));



        recyclerView.setAdapter(new CardAdapter(list));

    }
}