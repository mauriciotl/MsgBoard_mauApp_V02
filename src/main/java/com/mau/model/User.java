package com.mau.model;

import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String password;

    // Constructors

    public User() {
        // Default constructor (required for some frameworks like Jackson)
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Overridden methods (important for proper object comparison and use in collections)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // Example Usage
        User user1 = new User(1, "Alice", "password123");
        User user2 = new User(2, "Bob", "securePass");

        System.out.println(user1); // Output the user object
        System.out.println(user2);

        System.out.println("user1 equals user2? " + user1.equals(user2));
        User user3 = new User(1, "Alice", "password123");
        System.out.println("user1 equals user3? " + user1.equals(user3));

    }
}