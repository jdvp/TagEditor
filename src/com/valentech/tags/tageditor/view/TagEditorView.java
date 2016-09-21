package com.valentech.tags.tageditor.view;

import com.valentech.tags.tageditor.view.panels.MultiDirectoryPanel;
import com.valentech.tags.tageditor.view.panels.MultiFilePanel;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Gives users a way to actually see and customize tags
 */
public class TagEditorView extends JFrame{

    private MultiDirectoryPanel directories;
    private MultiFilePanel files;
    private ITagEditorV2MAdapter model;

    public TagEditorView(ITagEditorV2MAdapter modelIn){
        initGUI();
        model = modelIn;
    }

    public void start(){
        files = new MultiFilePanel(this);
        directories = new MultiDirectoryPanel(this);

        JSplitPane splitPane  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, directories, files);

        JScrollPane scrollPane = new JScrollPane(splitPane);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initGUI(){
        setSize(1000,750);
        setTitle("Tag Editor");
        setLayout(new BorderLayout());
    }

    public void setFileView(ArrayList<Path> fileList){
        System.out.println("set file view called with files = " + fileList);
        files.setFiles(fileList);
    }

    public ArrayList<String> getTags(Path file) {
        return model.getTags(file);
    }

    public void setTags(Path file, String tags){
        model.setTags(file, tags);
    }
}
