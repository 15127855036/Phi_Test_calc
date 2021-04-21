package com.augtons.tjuttestc8;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private List<EditTestTag> list;
    public MainAdapter(List<EditTestTag> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.edit_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextInputEditText editText = holder.editText;
        EditTestTag editTestTag = list.get(position);
        holder.textInputLayout.setHint("请输入：" + list.get(position).name + "的值");
        if (editTestTag.preText != null){
            holder.editText.setText(editTestTag.preText);
            editTestTag.preText = null;
        }
        list.get(position).editText = editText;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextInputEditText editText;
        public TextInputLayout textInputLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textInputLayout = itemView.findViewById(R.id.testInputLayoutMainList);
            editText = itemView.findViewById(R.id.editTextMainList);
        }
    }


    public static class EditTestTag {
        public String name;
        public TextInputEditText editText;
        public String preText = null;
        public EditTestTag (String name){
            this.name = name;
        }
    }
}
