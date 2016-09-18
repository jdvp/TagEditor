package com.valentech.tags.tageditor.view;

import com.valentech.tags.tageditor.view.panels.DirectoryView;

import javax.swing.*;
import java.awt.*;

/**
 * Gives users a way to actually see and customize tags
 */
public class TagEditorView extends JFrame{

    public TagEditorView(){
        initGUI();
    }

    public void start(){
        setVisible(true);
    }

    public void initGUI(){
        setSize(1000,750);
        setTitle("Tag Editor");
        setLayout(new FlowLayout());
        DirectoryView directoryView = new DirectoryView();
        JSplitPane splitPane  = new JSplitPane(JSplitPane.VERTICAL_SPLIT, directoryView, new JPanel());
        directoryView.setMinimumSize(new Dimension(1000, this.getHeight()/2));
        add(splitPane);

    }
}
