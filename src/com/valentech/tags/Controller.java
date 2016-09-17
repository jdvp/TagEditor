package com.valentech.tags;

import com.valentech.tags.tageditor.model.TagEditor;
import com.valentech.tags.tageditor.view.TagEditorView;

public class Controller {

    public static void main(String[] args) {
        TagEditor tagEditor = new TagEditor("D:/testFile1.txt");
        System.out.println("t "+ tagEditor.toString());
        tagEditor.getTags();
        tagEditor.getTags();

        TagEditorView view = new TagEditorView();
        view.start();
    }
}
