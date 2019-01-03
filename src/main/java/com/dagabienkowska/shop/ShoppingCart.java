package com.dagabienkowska.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingCart {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

}
