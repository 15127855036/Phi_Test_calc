package com.augtons.dianqiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    private List<Card> list;
    private Context context;
    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        Card card = list.get(position);
        holder.item.setText(card.itemName);
        holder.value.setText(card.itemValue);
        if (card.purplecolor) {
            holder.cardView.setCardBackgroundColor(context.getColor(R.color.item_color));
            holder.item.setTextColor(context.getColor(R.color.purple_700));
            holder.value.setTextColor(context.getColor(R.color.purple_700));
        }else {
            holder.cardView.setCardBackgroundColor(context.getColor(R.color.item_color_green));
            holder.item.setTextColor(context.getColor(R.color.teal_700));
            holder.value.setTextColor(context.getColor(R.color.teal_700));
        }
        holder.cardView.setOnClickListener(v -> {});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public CardAdapter(List<Card> list){
        this.list = list;
    }

    public static class CardHolder extends RecyclerView.ViewHolder {
        public TextView item;
        public TextView value;
        public CardView cardView;
        public CardHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.itemName);
            value = itemView.findViewById(R.id.itemValue);
            cardView = itemView.findViewById(R.id.cards);
        }
    }

    public static class Card{
        public String itemName;
        public String itemValue;
        public boolean purplecolor;
        public Card(String itemName, String itemValue, boolean purplecolor){
            this.itemName = itemName;
            this.itemValue = itemValue;
            this.purplecolor = purplecolor;
        }
    }
}
