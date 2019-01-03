package com.dagabienkowska.DAO.Impl;

import com.dagabienkowska.DAO.Spec.UserDao;
import com.dagabienkowska.Utils.JsonUtils;
import com.dagabienkowska.shop.JsonPOJO;
import com.dagabienkowska.shop.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserJsonDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserJsonDaoImpl.class.getName());//log z tej klasy


    @Override
    public boolean createUser(User user) {
        JsonPOJO jsonPOJO = JsonUtils.getJson();
        jsonPOJO.getUsers().add(user);
        JsonUtils.writeJson(jsonPOJO);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = JsonUtils.getJson().getUsers();
        LOGGER.log(Level.INFO, "Fetched all users");
        return users;
    }
}
