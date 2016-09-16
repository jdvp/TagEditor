package com.valentech.tags;

import com.valentech.tags.tageitor.TagEditor;

public class Main {

    public static void main(String[] args) {
        TagEditor tagEditor = new TagEditor("D:/testFile1.txt");
        System.out.println("t "+ tagEditor.toString());
        tagEditor.getTags();
        tagEditor.setTags();
        tagEditor.getTags();
    }
}
