package com.augtons.tjuttestc9;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Edit> list;
    private Context context;

    public MainAdapter(List<Edit> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.edit_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Edit edit = list.get(position);
        holder.textInputLayout1.setHint("请输入" + edit.name + "(左)的值");
        holder.textInputLayout2.setHint("请输入" + edit.name + "(右)的值");

        Object tag1 = holder.textInputEditText1.getTag();
        Object tag2 = holder.textInputEditText2.getTag();
        if (tag1 instanceof TextWatcher){
            holder.textInputEditText1.removeTextChangedListener((TextWatcher)tag1);
            holder.textInputEditText2.removeTextChangedListener((TextWatcher)tag2);
        }
        holder.textInputEditText1.setText(edit.value1);
        holder.textInputEditText2.setText(edit.value2);
        TextWatcher textWatcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edit.value1 = s.toString();
            }
        };
        TextWatcher textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edit.value2 = s.toString();
            }
        };
        holder.textInputEditText1.addTextChangedListener(textWatcher1);
        holder.textInputEditText2.addTextChangedListener(textWatcher2);
        holder.textInputEditText1.setTag(textWatcher1);
        holder.textInputEditText2.setTag(textWatcher2);

        if (edit.textInputEditText1 == null){
            edit.textInputEditText1 = holder.textInputEditText1;
        }
        if (edit.textInputEditText2 == null){
            edit.textInputEditText2 = holder.textInputEditText2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextInputLayout textInputLayout1;
        public TextInputEditText textInputEditText1;
        public TextInputLayout textInputLayout2;
        public TextInputEditText textInputEditText2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textInputEditText1 = itemView.findViewById(R.id.editTextMainList1);
            textInputEditText2 = itemView.findViewById(R.id.editTextMainList2);
            textInputLayout1 = itemView.findViewById(R.id.testInputLayoutMainList1);
            textInputLayout2 = itemView.findViewById(R.id.testInputLayoutMainList2);
        }
    }
    public static class Edit{
        TextInputEditText textInputEditText1;
        TextInputEditText textInputEditText2;
        public String value1 = "";
        public String value2 = "";
        public String name;
        public Edit(String name){
            this.name = name;
        }
    }
}
