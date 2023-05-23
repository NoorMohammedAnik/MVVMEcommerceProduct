package com.app.mvvmecommerceproduct.service.network;

import com.app.mvvmecommerceproduct.service.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //get customers data
    @GET("get_products.php")
    Call<List<ProductModel>> getProducts();
}
