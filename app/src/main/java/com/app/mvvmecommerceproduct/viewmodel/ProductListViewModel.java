package com.app.mvvmecommerceproduct.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.mvvmecommerceproduct.service.model.ProductModel;
import com.app.mvvmecommerceproduct.service.repository.ProductRepo;
import com.app.mvvmecommerceproduct.service.repository.ProductRepositoryImp;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {


    //AndroidView model give application contest but viewModel not
    private ProductRepo mRepo;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        mRepo= ProductRepositoryImp.getInstance(application);

    }

    public LiveData<List<ProductModel>> getProductList()
    {
       return mRepo.getProductList();
    }
}
