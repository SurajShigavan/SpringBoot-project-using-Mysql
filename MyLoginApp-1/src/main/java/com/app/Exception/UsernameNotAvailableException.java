package com.app.Exception;



import java.io.Serializable;

public class UsernameNotAvailableException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public UsernameNotAvailableException(String message) {
        super(message);
    }
}
