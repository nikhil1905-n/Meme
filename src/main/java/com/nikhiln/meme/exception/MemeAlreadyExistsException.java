package com.nikhiln.meme.exception;

public class MemeAlreadyExistsException extends RuntimeException {
    
    private static final String DEFAULT_MSG = "Meme Already Exists";

    public MemeAlreadyExistsException() {
        super(DEFAULT_MSG);
    }

    public MemeAlreadyExistsException(String msg) {
        super(msg);
    }

}
