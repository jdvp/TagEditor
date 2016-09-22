package com.valentech.tags.tageditor.view.panels;

import com.valentech.tags.tageditor.view.TagEditorView;

import javax.swing.*;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Allows for the user to see individual files and their tags.
 */
public class MultiFilePanel extends JPanel{

    private final TagEditorView parent;

    public MultiFilePanel(TagEditorView parentIn){
        parent = parentIn;
        initGUI();
    }


    /**
     * Initializes the UI
     */
    private void initGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets the list of files that this panel should display
     *
     * @param files Files to display to the user
     */
    public void setFiles(ArrayList<Path> files){
        removeAll();

        files.forEach(path -> add(new FilePanel(path, this)));
        revalidate();
        repaint();
    }

    /**
     * Returns the tags for a specified file
     * @param file The file to get tags for
     * @return The tags for the specified file
     */
    public ArrayList<String> getTags(Path file) {
        return parent.getTags(file);
    }

    /**
     * Sets the tags for a specified file
     * @param file The file to update tags on
     * @param tags The tags to set
     */
    public void setTags(Path file, String tags){
        parent.setTags(file, tags);
    }
}
