package com.example.hitechmart.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.hitechmart.base.BaseAdapter;
import com.example.hitechmart.base.BaseViewHolder;
import com.example.hitechmart.databinding.ItemDotBinding;
import com.example.hitechmart.model.Banner;
import com.example.hitechmart.model.ProductImage;

import java.util.ArrayList;
import java.util.Objects;

public class IndicatorAdapter extends BaseAdapter {


    private int size;

    private int selectedDotPosition = 0;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSelectedDotPosition() {
        return selectedDotPosition;
    }

    public void setSelectedDotPosition(int selectedDotPosition) {
        this.selectedDotPosition = selectedDotPosition;
    }

    public IndicatorAdapter(int size) {
        this.size = size;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDotBinding binding = ItemDotBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DotViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    class DotViewHolder extends BaseViewHolder<ItemDotBinding> {

        public DotViewHolder(ItemDotBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.dot.setSelected(selectedDotPosition == position);
        }
    }
}
