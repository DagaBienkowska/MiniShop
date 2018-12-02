package com.dagabienkowska.shop;

public class User {
    private long id;
    private String username;
    private String password;
    private long totalCashSpend;
    private String name;
    private String surname;
    private String role;

    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTotalCashSpend() {
        return totalCashSpend;
    }

    public void setTotalCashSpend(long totalCashSpend) {
        this.totalCashSpend = totalCashSpend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
