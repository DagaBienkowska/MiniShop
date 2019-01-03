package com.dagabienkowska.DAO.Impl;

import com.dagabienkowska.DAO.Spec.ShoppingCartDao;
import com.dagabienkowska.Utils.JsonUtils;
import com.dagabienkowska.shop.JsonPOJO;
import com.dagabienkowska.shop.ShoppingCart;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCartJsonDaoImpl implements ShoppingCartDao {

    private static final Logger LOGGER = Logger.getLogger(ShoppingCartJsonDaoImpl.class.getName());

    @Override
    public List<ShoppingCart> findAllCarts() {
        List<ShoppingCart> shoppingCarts = JsonUtils.getJson().getShoppingCarts();
        LOGGER.log(Level.INFO, "Fetched all carts: " + shoppingCarts);
        return shoppingCarts;
    }

    @Override
    public Optional<ShoppingCart> findCartByUsername(String username) {
        List<ShoppingCart> shoppingCarts = JsonUtils.getJson().getShoppingCarts();
        Optional<ShoppingCart> shoppingCart = shoppingCarts.stream()
                .filter(e -> e.getUsername().equals(username))
                .findFirst();
        return shoppingCart;
    }

    @Override
    public boolean saveOrUpdateCart(ShoppingCart shoppingCart) {
        JsonPOJO jsonAccess = JsonUtils.getJson();
        List<ShoppingCart> shoppingCarts = JsonUtils.getJson().getShoppingCarts();
        Optional<ShoppingCart> shoppingCartExisting = this.findCartByUsername(shoppingCart.getUsername());
        if (shoppingCartExisting.isPresent()) {
            int index = shoppingCarts.indexOf(shoppingCartExisting.get());
            shoppingCarts.set(index, shoppingCart);
        } else {
            shoppingCarts.add(shoppingCart);
        }
        jsonAccess.setShoppingCarts(shoppingCarts);
        JsonUtils.writeJson(jsonAccess);
        return true;
    }
}
