package com.example.hotelmitaddelmundo;

public class User {
    public String username;
    public String lastaname;
    public String email;
    public String id,url;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String lastaname, String email, String id) {
        this.username = username;
        this.lastaname = lastaname;
        this.email = email;
        this.id = id;
    }

    public User(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastaname() {
        return lastaname;
    }

    public void setLastaname(String lastaname) {
        this.lastaname = lastaname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
