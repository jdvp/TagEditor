package com.valentech.tags;

import com.valentech.tags.tageditor.model.TagEditorModel;
import com.valentech.tags.tageditor.view.TagEditorView;

public class Controller {

    public static void main(String[] args) {
//        TagEditor tagEditor = new TagEditor("C:/Users/JD/Desktop/c1.PNG");
//        System.out.println("t "+ tagEditor.toString());
//        tagEditor.getTags();
//        ArrayList<String> tags =  new ArrayList<>();
//        tags.addAll(Arrays.asList("what", "is going", "on"));
//
//        tagEditor.setTags(tags);

        TagEditorModel model = new TagEditorModel();
        TagEditorView view = new TagEditorView(file -> model.getTags(file));
        view.start();
    }
}
