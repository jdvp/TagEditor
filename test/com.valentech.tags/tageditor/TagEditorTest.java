package com.valentech.tags.tageditor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by JD on 9/16/2016.
 */
public class TagEditorTest {

    @Test
    public void testSetGetClear(){
        String filePath = this.getClass().getResource("testTagFile.txt").getPath().replaceFirst("/", "");
        TagEditor tagEditor = new TagEditor(filePath);
        tagEditor.clearTags();

        String expected ="[what,is going,on]";
        ArrayList<String> tags =  new ArrayList<>();
        tags.addAll(Arrays.asList("what", "is going", "on"));

        tagEditor.setTags(tags);
        assertEquals(expected, TagParser.unparse(tagEditor.getTags()));
    }

    @Test
    public void testAdd(){
        String filePath = this.getClass().getResource("testTagFile.txt").getPath().replaceFirst("/", "");
        TagEditor tagEditor = new TagEditor(filePath);
        tagEditor.clearTags();

        assertEquals("[]", TagParser.unparse(tagEditor.getTags()));

        tagEditor.addTag("hello");
        assertEquals("[hello]", TagParser.unparse(tagEditor.getTags()));

        tagEditor.addTag("jd porterfield");
        assertEquals("[hello,jd porterfield]", TagParser.unparse(tagEditor.getTags()));
    }

}
