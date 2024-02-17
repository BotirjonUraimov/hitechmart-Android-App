package com.example.hitechmart.base;

import com.example.hitechmart.adapter.ClassificationAdapter;

public interface BaseAdapterListener {
    void onCategoryClick(int id,String title, ClassificationAdapter.Type type);
}
