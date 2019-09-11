package com.ideas2it.ism.exception;

public class IsmException extends Exception{

    /**
     * Constructs the custom exception using the given message
     *
     * @param message - Thrown message at the time of throwing custom exception
     */
    public IsmException(String message) {
        super(message);
    }

    /**
     * Constructs the custom exception using the given message and the exception class
     *
     * @param message - Thrown message at the time of throwing custom exception
     * @param cause - Exception class while handling the exception
     */
    public IsmException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs the custom exception using the exception class
     *
     * @param cause - Exception class while handling the exception
     */
    public IsmException(Throwable cause) {
        super(cause);
    }
}