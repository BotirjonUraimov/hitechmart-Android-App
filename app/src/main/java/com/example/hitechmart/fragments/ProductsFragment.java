package com.example.hitechmart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hitechmart.adapter.ClassificationAdapter;
import com.example.hitechmart.base.BaseAdapterListener;
import com.example.hitechmart.base.BaseFragment;

import com.example.hitechmart.databinding.FragmentProductsBinding;
import com.example.hitechmart.model.Category;
import com.example.hitechmart.model.Classification;

import com.example.hitechmart.model.Subproduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends BaseFragment<FragmentProductsBinding> {

    private ArrayList<Classification> classificationArrayList;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Subproduct> subproductArrayList;
    private ClassificationAdapter adapter;

    @Override
    protected FragmentProductsBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentProductsBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classificationArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        subproductArrayList = new ArrayList<>();
        adapter = new ClassificationAdapter(classificationArrayList, categoryArrayList, subproductArrayList);
        adapter.setListener(new BaseAdapterListener() {

            @Override
            public void onCategoryClick(int id, ClassificationAdapter.Type type) {
                if (type == ClassificationAdapter.Type.CLASSIFICATION) {
                    binding.tvBackBtn.setVisibility(View.VISIBLE);
                    binding.line.setVisibility(View.VISIBLE);
                    binding.tvBackBtn.setText("Classification");
                    loadCategory(id);
                } else if (type == ClassificationAdapter.Type.CATEGORY) {
                    binding.tvBackBtn.setVisibility(View.VISIBLE);
                    binding.line.setVisibility(View.VISIBLE);
                    binding.tvBackBtn.setText("Category");
                    loadSubproduct(id);
                }
            }
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.categoryRecyclerView.setAdapter(adapter);

        binding.tvBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassificationAdapter.Type type = adapter.moveBack();
                if (type == ClassificationAdapter.Type.CATEGORY) {
                    binding.tvBackBtn.setText("Classification");
                } else if (type == ClassificationAdapter.Type.CLASSIFICATION){
                    binding.tvBackBtn.setVisibility(View.GONE);
                    binding.line.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadClassification();
    }

    private void loadClassification() {
        Call<ArrayList<Classification>> call = parent.mainApi.getClassifications();

        call.enqueue(new Callback<ArrayList<Classification>>() {
            @Override
            public void onResponse(Call<ArrayList<Classification>> call, Response<ArrayList<Classification>> response) {
                if (response.isSuccessful()) {
                    classificationArrayList.clear();
                    classificationArrayList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Classification>> call, Throwable t) {

            }
        });
    }

    private void loadCategory(int classificationId) {
        categoryArrayList.clear();
        adapter.changeType(ClassificationAdapter.Type.CATEGORY);
        Call<ArrayList<Category>> call = parent.mainApi.getCategoryWithClassification(classificationId);

        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {

                    categoryArrayList.addAll(response.body());
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

            }
        });
    }

    private void loadSubproduct(int categoryId) {
        subproductArrayList.clear();
        Call<ArrayList<Subproduct>> call = parent.mainApi.getSubproductWithCategory(categoryId);
        adapter.changeType(ClassificationAdapter.Type.SUBPRODUCT);

        call.enqueue(new Callback<ArrayList<Subproduct>>() {
            @Override
            public void onResponse(Call<ArrayList<Subproduct>> call, Response<ArrayList<Subproduct>> response) {
                if (response.isSuccessful()) {

                    subproductArrayList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Subproduct>> call, Throwable t) {

            }
        });
    }
}
