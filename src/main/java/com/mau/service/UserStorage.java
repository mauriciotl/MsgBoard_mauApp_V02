package com.mau.service;

import com.mau.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserStorage {

    private final List<User> users;

    // Static initialization block to populate initial users
    static List<User> initialUsers = new ArrayList<>();
    static {
        initialUsers.add(new User(1, "Alice", "password1"));
        initialUsers.add(new User(2, "Bob", "password2"));
        initialUsers.add(new User(3, "Charlie", "password3"));
        initialUsers.add(new User(4, "David", "password4"));
        initialUsers.add(new User(5, "Eve", "password5"));
    }

    public UserStorage() {
        this.users = new ArrayList<>(initialUsers); // Create a copy
    }

    // CRUD Operations (same as before)

    public List<User> getAllUsers() {
        return new ArrayList<>(users); // Return a copy
    }

    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public User createUser(User user) {
        int nextId = users.stream().mapToInt(User::getId).max().orElse(0) + 1;
        user.setId(nextId);
        users.add(user);
        return user;
    }

    public Optional<User> updateUser(int id, User updatedUser) {
        return getUserById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setPassword(updatedUser.getPassword());
            return existingUser;
        });
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    // Other helpful methods (same as before)

    public List<User> searchUsersByName(String name) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean userExists(int id){
        return getUserById(id).isPresent();
    }
}