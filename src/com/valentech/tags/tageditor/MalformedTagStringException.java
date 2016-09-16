package com.valentech.tags.tageditor;

/**
 * Error that specifies that a tag string is broken or malformed
 */
public class MalformedTagStringException extends Exception {
    public MalformedTagStringException(String error){
        super(error);
    }
}
