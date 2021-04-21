package com.augtons.dianqiao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Output extends AppCompatActivity {
    RecyclerView recyclerView;
    Intent intent;
    List<CardAdapter.Card> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        recyclerView = findViewById(R.id.RecyclerView1);
        intent = getIntent();
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        GetOutPut getOutPut = intent.getParcelableExtra("OutPut");
        String te = String.valueOf(getOutPut.cap_s);
        list.add(new CardAdapter.Card("S = ", String.valueOf(getOutPut.cap_s), true));
        list.add(new CardAdapter.Card("Δs = ", String.valueOf(getOutPut.delta_s), true));
        list.add(new CardAdapter.Card("Δa = ", String.valueOf(getOutPut.delta_a), true));
        list.add(new CardAdapter.Card("ΔRx = ", String.valueOf(getOutPut.delta_rx), true));

        list.add(new CardAdapter.Card("*ΔR1 = ", String.valueOf(getOutPut.delta_r1), false));
        list.add(new CardAdapter.Card("*ΔR2 = ", String.valueOf(getOutPut.delta_r2), false));
        list.add(new CardAdapter.Card("*ΔR0 = ", String.valueOf(getOutPut.delta_r0), false));

        list.add(new CardAdapter.Card("*Er = ", String.valueOf(getOutPut.cap_Er), false));

        CardAdapter cardAdapter = new CardAdapter(list);
        recyclerView.setAdapter(cardAdapter);
    }
}