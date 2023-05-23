package com.app.mvvmecommerceproduct.service.repository;

import androidx.lifecycle.MutableLiveData;

import com.app.mvvmecommerceproduct.service.model.ProductModel;

import java.util.List;

public interface ProductRepo {


    public MutableLiveData<List<ProductModel>> getProductList();
}
