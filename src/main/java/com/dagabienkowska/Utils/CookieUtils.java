package com.dagabienkowska.Utils;

import com.dagabienkowska.shop.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static void createUserCookie(HttpServletResponse response, User user){
        Cookie cookie = new Cookie("user", user.getUsername());
        cookie.setMaxAge(30*60);
        response.addCookie(cookie);

    }
}
