package com.mobi.urbandictionary.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.urbandictionary.R;
import com.mobi.urbandictionary.data.ItemDefinition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder> {

    private List<ItemDefinition> allDef;

    public DefinitionAdapter(List<ItemDefinition> definitions) {
        allDef = definitions;
    }


    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_definition, viewGroup, false);
        return new DefinitionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder remainderViewHolder, int i) {
        remainderViewHolder.btnThumbUp.setText(String.valueOf(allDef.get(i).getThumbsUp()));
        remainderViewHolder.btnThumbDown.setText(String.valueOf(allDef.get(i).getThumbsDown()));
        remainderViewHolder.tvDef.setText(allDef.get(i).getDefinition());
        remainderViewHolder.tvExample.setText(allDef.get(i).getExample());
    }

    @Override
    public int getItemCount() {
        return allDef.size();
    }


    class DefinitionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_def)
        TextView tvDef;
        @BindView(R.id.tv_example)
        TextView tvExample;
        @BindView(R.id.btn_thumb_up)
        Button btnThumbUp;
        @BindView(R.id.btn_thumb_down)
        Button btnThumbDown;

        DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
