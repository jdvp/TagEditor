package com.valentech.tags.tageditor.view.panels;

import com.valentech.tags.tageditor.view.TagEditorView;

import javax.swing.*;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by JD on 9/18/2016.
 */
public class MultiFilePanel extends JPanel{

    private TagEditorView parent;

    public MultiFilePanel(TagEditorView parentIn){
        parent = parentIn;
        initGUI();
    }


    public void initGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setFiles(ArrayList<Path> files){
        removeAll();

        files.forEach(path -> add(new FilePanel(path, this)));
        revalidate();
        repaint();
    }

    public ArrayList<String> getTags(Path file) {
        return parent.getTags(file);
    }
}
