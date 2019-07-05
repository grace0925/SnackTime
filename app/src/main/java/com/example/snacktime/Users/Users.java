package com.example.snacktime.Users;

public class Users {
    private String Username, Firstname, Lastname, Email, Password;

    public Users() {

    }

    public Users(String username, String firstname, String lastname, String email, String password) {
        this.Username = username;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Email = email;
        this.Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
