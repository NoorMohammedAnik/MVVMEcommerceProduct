package com.app.mvvmecommerceproduct.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mvvmecommerceproduct.R;
import com.app.mvvmecommerceproduct.service.model.ProductModel;
import com.app.mvvmecommerceproduct.view.adapter.ProductListAdapter;
import com.app.mvvmecommerceproduct.viewmodel.ProductListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductListViewModel productListViewModel;
    private ProductListAdapter productListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);


        productListViewModel=new ViewModelProvider(this).get( ProductListViewModel.class);
        productListViewModel.getProductList().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                productListAdapter= new ProductListAdapter(MainActivity.this,productModels);
                recyclerView.setAdapter(productListAdapter);

            }
        });
    }
}