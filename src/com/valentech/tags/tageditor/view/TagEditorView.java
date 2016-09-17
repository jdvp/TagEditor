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
        setLayout(new BorderLayout());
        DirectoryView a = new DirectoryView();
    }
}
