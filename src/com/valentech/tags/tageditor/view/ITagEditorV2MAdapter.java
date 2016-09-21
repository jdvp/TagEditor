package com.valentech.tags.tageditor.view;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Adapter that the tag editor view uses to communicate with the model.
 */
public interface ITagEditorV2MAdapter {
    ITagEditorV2MAdapter NULL_ADAPTER = new ITagEditorV2MAdapter() {
        @Override
        public ArrayList<String> getTags(Path file) {return new ArrayList<>();}

        @Override
        public void setTags(Path file, String tags) {}
    };

    ArrayList<String> getTags(Path file);

    void setTags(Path file, String tags);

}
