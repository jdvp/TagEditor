package com.valentech.tags.tageditor.exceptions;

/**
 * Error that a specific tag is too long.
 * 'Too long' means that the tag is larger than the longest allowable string length - 2 (for the start and end bracket)
 */
public class TagTooLongException extends RuntimeException {
    public TagTooLongException(String error){
        super(error);
    }
}
