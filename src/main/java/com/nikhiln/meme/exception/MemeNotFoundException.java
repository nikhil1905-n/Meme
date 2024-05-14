package com.nikhiln.meme.exception;

public class MemeNotFoundException extends RuntimeException {
    
    private static final String DEFAULT_MSG = "Meme Not Found";

    public MemeNotFoundException() {
        super(DEFAULT_MSG);
    }

    public MemeNotFoundException(String msg) {
        super(msg);
    }
    
}
