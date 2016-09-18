package com.valentech.tags.tageditor.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JD on 9/18/2016.
 */
public class TagEditorModel {

    private HashMap<Path, TagEditor> files;

    public TagEditorModel(){
        files = new HashMap<>();
    }

    public ArrayList<String> getTags(Path path){
        files.computeIfAbsent(path, TagEditor::new);

        return files.get(path).getTags();
    }
}
