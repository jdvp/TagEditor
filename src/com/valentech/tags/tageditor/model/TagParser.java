package com.valentech.tags.tageditor.model;

import com.valentech.tags.tageditor.exceptions.MalformedTagStringException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Class used to parse and unparse tags between tag list strings and tag array lists
 */
public class TagParser {

    private static final String DELIMITER = ",";
    private static final String NON_ALLOWED_REGEX = "[^A-Za-z0-9, ]";

    private TagParser(){}

    /**
     * Parse the passed in tags
     * @param tags A string of tags, probably retrieved from a file
     * @return An ArrayList of individual tags
     * @throws MalformedTagStringException
     */
    public static ArrayList<String> parse(String tags) throws MalformedTagStringException {

        //tags are already malformed if they aren't bracketed
        if(!tags.startsWith("[") || !tags.endsWith("]")){
            throw new MalformedTagStringException("Beginning or ending characters incorrect.");
        }

        //replace all non-alphanumeric characters, spaces and commas
        tags = tags.replaceAll(NON_ALLOWED_REGEX, "");

        //delimit and trim the tags
        List<String> delimited = Arrays.asList(tags.split(DELIMITER));
        ArrayList<String> result = new ArrayList<>();
        delimited.forEach(s -> {
            if(s.trim().length() > 0)
                result.add(s.trim());
        });

        return result;
    }

    /**
     * 'Unparses' an ArrayList of tags into a string that can be saved to file attributes
     * @param tags an ArrayList of tags to save
     * @return the string representation of the input
     */
    public static String unparse(ArrayList<String> tags){
        StringJoiner stringJoiner = new StringJoiner(",","[", "]");
        tags.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
