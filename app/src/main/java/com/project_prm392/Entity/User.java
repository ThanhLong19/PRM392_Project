package com.project_prm392.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String username;
    private String phone;
    private String address;
    private String password;
    private int role;

    public User(int id, String email, String username, String phone, String address, String password, int role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public User(String email, String username, String phone, String address, String password, int role) {
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public User(String email, String username, String phone, String address, String password) {
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
