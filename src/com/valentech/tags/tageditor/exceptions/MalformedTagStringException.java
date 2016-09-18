package com.valentech.tags.tageditor.exceptions;

/**
 * Error that specifies that a tag string is broken or malformed
 */
public class MalformedTagStringException extends Exception {
    public MalformedTagStringException(){
        super("Beginning or ending characters incorrect.");
    }
}
