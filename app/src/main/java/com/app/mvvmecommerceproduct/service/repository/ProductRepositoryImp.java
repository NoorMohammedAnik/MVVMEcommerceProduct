package com.app.mvvmecommerceproduct.service.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.app.mvvmecommerceproduct.service.model.ProductModel;
import com.app.mvvmecommerceproduct.service.network.ApiClient;
import com.app.mvvmecommerceproduct.service.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImp implements ProductRepo {

    private static Context mcontext;
    private static ProductRepositoryImp instance;
    private List<ProductModel> productList;
    private ProductModel productModel;
    private MutableLiveData mLiveData;

    public static ProductRepositoryImp getInstance(Context context) {
        if (instance == null) {
            mcontext = context;
            instance = new ProductRepositoryImp();
        }
        return instance;

    }



    @Override
    public MutableLiveData<List<ProductModel>> getProductList() {

        if (mLiveData == null) {

            mLiveData = new MutableLiveData();
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ProductModel>> call = apiInterface.getProducts();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {

                productList = response.body();
                mLiveData.postValue(productList);


            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });

        return mLiveData;

    }
}
