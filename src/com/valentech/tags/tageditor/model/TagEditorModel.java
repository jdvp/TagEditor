package com.valentech.tags.tageditor.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The model that hold the currently running programs recently visited paths in memory.
 * This allows for quicker operations on them should the user request to do operations on them.
 */
public class TagEditorModel {

    /**
     * The files that we have mapped so far in our directory traversal
     */
    private final HashMap<Path, TagEditor> files;

    /**
     * Constructor
     */
    public TagEditorModel(){
        files = new HashMap<>();
    }

    /**
     * Creates a new TagEditor if we haven't seen a specified path before.
     * Then the tags for the file are retrieved.
     * @param path The path to a file to get tags
     * @return Tags on the specified file
     */
    public ArrayList<String> getTags(Path path){
        files.computeIfAbsent(path, TagEditor::new);

        return files.get(path).getTags();
    }

    /**
     * Sets the specified tags on the specified path
     * @param path The path to set tags for
     * @param tags The tags to set
     */
    public void setTags(Path path, String tags){
        files.get(path).setTags(tags);
    }
}
