package com.mau.service;

import com.mau.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage {

    // Logger for the class
    private static final Logger logger = LogManager.getLogger(MessageStorage.class);

    // Instance variable to store messages
    private List<Message> listMessages;

    // Constructor with a specified List type
    public MessageStorage(List<Message> listMessages) {
        if (listMessages == null) {
            logger.error("List implementation cannot be null");
            throw new IllegalArgumentException("List implementation cannot be null");
        }
        this.listMessages = listMessages;
        logger.info("MessageStorage initialized with a custom list");
    }

    // Default constructor uses ArrayList
    public MessageStorage() {
        this(new ArrayList<>());
        logger.info("MessageStorage initialized with default ArrayList");
    }

    /**
     * Adds a new message to the storage.
     * @param message the Message object to add
     */
    public void addMessage(Message message) {
        if (message == null) {
            logger.warn("Attempted to add a null message");
            return;
        }
        listMessages.add(message);
        logger.debug("Message added: {}", message);
    }

    /**
     * Retrieves a message by its ID.
     * @param id the ID of the message to retrieve
     * @return the Message object, or null if not found
     */
    public Message getMessageById(int id) {
        logger.debug("Retrieving message with ID: {}", id);
        for (Message message : listMessages) {
            if (message.getId() == id) {
                logger.info("Message found: {}", message);
                return message;
            }
        }
        logger.warn("Message with ID {} not found", id);
        return null;
    }

    /**
     * Updates an existing message by its ID.
     * @param id the ID of the message to update
     * @param newMessage the new Message object to replace the old one
     * @return true if the update was successful, false otherwise
     */
    public boolean updateMessage(int id, Message newMessage) {
        if (newMessage == null) {
            logger.warn("Attempted to update a message with null");
            return false;
        }
        for (int i = 0; i < listMessages.size(); i++) {
            if (listMessages.get(i).getId() == id) {
                logger.debug("Updating message with ID: {}", id);
                listMessages.set(i, newMessage);
                logger.info("Message updated: {}", newMessage);
                return true;
            }
        }
        logger.warn("Message with ID {} not found for update", id);
        return false;
    }

    /**
     * Deletes a message by its ID.
     * @param id the ID of the message to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteMessage(int id) {
        logger.debug("Attempting to delete message with ID: {}", id);
        boolean result = listMessages.removeIf(message -> message.getId() == id);
        if (result) {
            logger.info("Message with ID {} deleted successfully", id);
        } else {
            logger.warn("Message with ID {} not found for deletion", id);
        }
        return result;
    }

    /**
     * Retrieves all stored messages.
     * @return a list of all messages
     */
    public List<Message> getAllMessages() {
        logger.debug("Retrieving all messages");
        return new ArrayList<>(listMessages);
    }

    /**
     * Clears all messages from the storage.
     */
    public void clearMessages() {
        logger.info("Clearing all messages");
        listMessages.clear();
    }

    /**
     * Counts the total number of messages in the storage.
     * @return the total count of messages
     */
    public int countMessages() {
        int count = listMessages.size();
        logger.debug("Counted {} messages in storage", count);
        return count;
    }
}
