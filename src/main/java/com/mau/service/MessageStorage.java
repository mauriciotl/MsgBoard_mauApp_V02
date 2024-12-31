package com.mau.service;

import com.mau.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage {

    // Instance variable to store messages
    private List<Message> listMessages;

    // Constructor with a specified List type
    public MessageStorage(List<Message> listMessages) {
        if (listMessages == null) {
            throw new IllegalArgumentException("List implementation cannot be null");
        }
        this.listMessages = listMessages;
    }

    // Default constructor uses ArrayList
    public MessageStorage() {
        this(new ArrayList<>());
    }

    /**
     * Adds a new message to the storage.
     * @param message the Message object to add
     */
    public void addMessage(Message message) {
        listMessages.add(message);
    }

    /**
     * Retrieves a message by its ID.
     * @param id the ID of the message to retrieve
     * @return the Message object, or null if not found
     */
    public Message getMessageById(int id) {
        for (Message message : listMessages) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    /**
     * Updates an existing message by its ID.
     * @param id the ID of the message to update
     * @param newMessage the new Message object to replace the old one
     * @return true if the update was successful, false otherwise
     */
    public boolean updateMessage(int id, Message newMessage) {
        for (int i = 0; i < listMessages.size(); i++) {
            if (listMessages.get(i).getId() == id) {
                listMessages.set(i, newMessage);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a message by its ID.
     * @param id the ID of the message to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteMessage(int id) {
        return listMessages.removeIf(message -> message.getId() == id);
    }

    /**
     * Retrieves all stored messages.
     * @return a list of all messages
     */
    public List<Message> getAllMessages() {
        return new ArrayList<>(listMessages);
    }

    /**
     * Clears all messages from the storage.
     */
    public void clearMessages() {
        listMessages.clear();
    }

    /**
     * Counts the total number of messages in the storage.
     * @return the total count of messages
     */
    public int countMessages() {
        return listMessages.size();
    }
}
