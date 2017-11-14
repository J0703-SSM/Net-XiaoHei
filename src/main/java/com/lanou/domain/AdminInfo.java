package com.lanou.domain;

import java.util.Date;

/**
 * Created by dllo on 17/11/11.
 */
public class AdminInfo {

    private int id;

    private String admin_code;

    private String password;

    private String name;

    private String telephone;

    private String email;

    private Date enrollDate;

    public AdminInfo() {
    }

    public AdminInfo(String admin_code, String password, String email) {
        this.admin_code = admin_code;
        this.password = password;
        this.email = email;
    }

    public AdminInfo(int id, String admin_code, String password, String name, String telephone, String email) {
        this.id = id;
        this.admin_code = admin_code;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    public AdminInfo(int id, String admin_code, String password, String name, String telephone, String email, Date enrollDate) {
        this.id = id;
        this.admin_code = admin_code;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.enrollDate = enrollDate;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "id=" + id +
                ", admin_code='" + admin_code + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", enrollDate=" + enrollDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdmin_code() {
        return admin_code;
    }

    public void setAdmin_code(String admin_code) {
        this.admin_code = admin_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }
}
