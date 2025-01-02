package com.mau.utils;

public class IdGenerator {

    // Static variable to keep track of the current ID
    private static int currentId = 1;

    /**
     * Generates the next ID.
     * @return the next unique ID starting from 1
     */
    public static synchronized int generateId() {
        return currentId++;
    }

    /**
     * Resets the ID counter to 1. This can be useful for testing or resetting the state.
     */
    public static void reset() {
        currentId = 1;
    }
}

