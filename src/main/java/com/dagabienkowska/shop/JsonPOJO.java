package com.dagabienkowska.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class JsonPOJO {
    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("shoppingCarts")
    @Expose
    private List<ShoppingCart> shoppingCarts = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
