package com.valentech.tags.tageditor.model;

import com.valentech.tags.tageditor.exceptions.MalformedTagStringException;
import com.valentech.tags.tageditor.model.TagParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by JD on 9/16/2016.
 */
public class TagParserTest {

    @Test
    public void testParseEmpty() throws Exception {
        String emptyTagList = "[]";

        assertEquals(0, TagParser.parse(emptyTagList).size());
    }

    @Test
    public void testParseEmptyMalformed() throws Exception {
        String malformed1 = "";
        String malformed2 = "[";

        try {
            TagParser.parse(malformed1);
            fail("Expected an exception");
        } catch (MalformedTagStringException ignored){}

        try {
            TagParser.parse(malformed2);
            fail("Expected an exception");
        } catch (MalformedTagStringException ignored){}
    }

    @Test
    public void testUnparse() throws Exception {
        String expected ="[what,is going,on]";
        ArrayList<String> tags =  new ArrayList<>();
        tags.addAll(Arrays.asList("what", "is going", "on"));

        assertEquals(expected, TagParser.unparse(tags));
    }
}