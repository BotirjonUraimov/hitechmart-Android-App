package com.example.hitechmart.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.hitechmart.activity.ProductDetailsActivity;
import com.example.hitechmart.base.BaseAdapter;
import com.example.hitechmart.base.BaseAdapterListener;
import com.example.hitechmart.base.BaseViewHolder;
import com.example.hitechmart.databinding.ItemProductBinding;
import com.example.hitechmart.model.Product;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {


    private ArrayList<Product> productsArrayList;

    public ProductListAdapter(ArrayList<Product> productsArrayList) {
        this.productsArrayList = productsArrayList;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public class ProductViewHolder extends BaseViewHolder<ItemProductBinding> {


        public ProductViewHolder(ItemProductBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            Product product = productsArrayList.get(position);

            binding.productName.setText(product.getTitle());
            binding.productBrand.setText(product.getBrand());
            binding.productPriceCurrent.setText("$" + product.getPriceCurrent());
            binding.productPriceOriginal.setText("$" + product.getPriceOriginal());

            if (product.getImage() != null && product.getImage().getImage() != null)
                Glide.with(binding.productImage).load(product.getImage().getImage()).into(binding.productImage);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(binding.getRoot().getContext(), ProductDetailsActivity.class);
                    int id = productsArrayList.get(position).getId();
                    // Product model Serializable dan implement qilinishi kerak
                    intent.putExtra("product", product);
                    binding.getRoot().getContext().startActivity(intent);


                }
            });
        }
    }
}
