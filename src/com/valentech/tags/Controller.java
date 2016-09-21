package com.valentech.tags;

import com.valentech.tags.tageditor.model.TagEditorModel;
import com.valentech.tags.tageditor.view.ITagEditorV2MAdapter;
import com.valentech.tags.tageditor.view.TagEditorView;

import java.nio.file.Path;
import java.util.ArrayList;

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
        TagEditorView view = new TagEditorView(new ITagEditorV2MAdapter() {
            @Override
            public ArrayList<String> getTags(Path file) {
                return model.getTags(file);
            }

            @Override
            public void setTags(Path file, String tags) {
                model.setTags(file, tags);
            }
        });
        view.start();
    }
}
