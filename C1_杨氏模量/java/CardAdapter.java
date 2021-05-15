package com.augtons.tjuttestc1;

import android.app.AlertDialog;
import android.content.Context;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Card> list;
    private Context context;
    public CardAdapter(List<Card> list){
        this.list = list;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = list.get(position);
        holder.itemName.setText(card.itemName);
        holder.itemValue.setText(card.itemValue);
        if (card.isPurpal){
            holder.cardView.setCardBackgroundColor(context.getColor(R.color.item_color));
            holder.itemName.setTextColor(context.getColor(R.color.purple_700));
            holder.itemValue.setTextColor(context.getColor(R.color.purple_700));
        }else {
            holder.cardView.setCardBackgroundColor(context.getColor(R.color.item_color_green));
            holder.itemName.setTextColor(context.getColor(R.color.teal_700));
            holder.itemValue.setTextColor(context.getColor(R.color.teal_700));
        }
        holder.cardView.setOnClickListener(v -> {
            if (card.onClickListener != null) {
                card.onClickListener.onClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName;
        public TextView itemValue;
        public CardView cardView;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemValue = itemView.findViewById(R.id.itemValue);
            cardView = itemView.findViewById(R.id.cards);
        }
    }

    public static class Card{
        public String itemName;
        public String itemValue;
        public boolean isPurpal;
        private OnClickListener onClickListener;
        public Card(String itemName, String itemValue, boolean isPurpal){
            this.itemName = itemName;
            this.itemValue = itemValue;
            this.isPurpal = isPurpal;
        }

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public interface OnClickListener{
            void onClick();
        };
    }
}