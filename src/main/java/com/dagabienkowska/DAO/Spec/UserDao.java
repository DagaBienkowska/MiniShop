package com.dagabienkowska.DAO.Spec;

import com.dagabienkowska.shop.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserDao {

    boolean createUser(User user);
    List<User> getAllUsers();
}
