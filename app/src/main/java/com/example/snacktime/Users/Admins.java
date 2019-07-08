package com.example.snacktime.Users;

public class Admins {
    private String AdminID, Password;

    public void Admins() {

    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        this.AdminID = adminID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Admins(String adminID, String password) {
        this.AdminID = adminID;
        this.Password = password;
    }
}
