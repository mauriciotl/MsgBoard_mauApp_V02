package com.mau.model;

import java.util.Date;

public class Message {

    // Instance variables
    private int id;
    private String user;
    private String name;
    private String description;
    private Date issuedDate;

    // Constructor
    public Message(int id, String user, String name, String description, Date issuedDate) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.issuedDate = issuedDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    // toString method for easy representation
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
