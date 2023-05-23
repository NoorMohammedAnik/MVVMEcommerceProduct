package com.app.mvvmecommerceproduct.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mvvmecommerceproduct.Constant;
import com.app.mvvmecommerceproduct.R;
import com.app.mvvmecommerceproduct.service.model.ProductModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private Context context;
    private List<ProductModel> productList;

    public ProductListAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.MyViewHolder holder, int position) {

        String productId = productList.get(position).getProductId();
        String productName = productList.get(position).getProductName();
        String productWeight = productList.get(position).getProductWeight();
        String productPrice = productList.get(position).getProductPrice();
        String weightUnit = productList.get(position).getWeightUnitName();
        String productImage = productList.get(position).getProductImage();
        String productStock = productList.get(position).getProductStock();

        String imageUrl = Constant.PRODUCT_IMAGE_URL + productImage;


        holder.txtProductName.setText(productName);
        holder.txtWeight.setText(productWeight + " " + weightUnit);
        holder.txtPrice.setText(Constant.Currency + productPrice);


        //Low stock marked as RED color
        int getStock = Integer.parseInt(productStock);
        if (getStock > 5) {
            holder.txtStock.setText(context.getString(R.string.stock) + " : " + productStock);
            holder.txtStockStatus.setVisibility(View.VISIBLE);

            holder.txtStockStatus.setBackgroundColor(Color.parseColor("#43a047"));
            holder.txtStockStatus.setText(context.getString(R.string.in_stock));
        } else if (getStock == 0) {
            holder.txtStock.setText(context.getString(R.string.stock) + " : " + productStock);
            holder.btnAddToCart.setText(R.string.out_of_stock);
            holder.btnAddToCart.setBackgroundColor(Color.RED);
            holder.txtStock.setTextColor(Color.RED);

            holder.txtStockStatus.setVisibility(View.VISIBLE);
            holder.txtStockStatus.setText(context.getString(R.string.not_available));

        } else {
            holder.txtStock.setText(context.getString(R.string.stock) + " : " + productStock);
            holder.txtStock.setTextColor(Color.RED);
            holder.txtStockStatus.setVisibility(View.VISIBLE);
            holder.txtStockStatus.setText(context.getString(R.string.low_stock));


        }

        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(context, productName, Toast.LENGTH_SHORT).show();
            }
        });


        if (productImage != null) {
            if (productImage.length() < 3) {

                holder.productImage.setImageResource(R.drawable.image_placeholder);
            } else {


                Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.image_placeholder)
                        .into(holder.productImage);

            }
        }


    }

    @Override
    public int getItemCount() {

        return productList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardProduct;
        TextView txtProductName, txtWeight, txtPrice, txtStock, txtStockStatus;
        Button btnAddToCart;
        ImageView productImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txt_product_name);
            txtWeight = itemView.findViewById(R.id.txt_weight);
            txtStock = itemView.findViewById(R.id.txt_stock);
            txtPrice = itemView.findViewById(R.id.txt_price);
            productImage = itemView.findViewById(R.id.img_product);
            btnAddToCart = itemView.findViewById(R.id.btn_add_cart);
            cardProduct = itemView.findViewById(R.id.card_product);
            txtStockStatus = itemView.findViewById(R.id.txt_stock_status);
        }
    }
}
