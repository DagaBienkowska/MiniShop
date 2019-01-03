package com.dagabienkowska.DAO.Spec;

import com.dagabienkowska.shop.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {

    List<ShoppingCart> findAllCarts();
    Optional<ShoppingCart> findCartByUsername(String username);
    boolean saveOrUpdateCart(ShoppingCart shoppingCart);
}
