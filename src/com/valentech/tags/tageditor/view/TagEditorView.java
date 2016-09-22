package com.valentech.tags.tageditor.view;

import com.valentech.tags.tageditor.view.panels.MultiDirectoryPanel;
import com.valentech.tags.tageditor.view.panels.MultiFilePanel;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Gives users a way to actually see and customize tags
 */
public class TagEditorView extends JFrame{

    private MultiDirectoryPanel directories;
    private MultiFilePanel files;
    private ITagEditorV2MAdapter model;
    private final JLabel currentName = new JLabel();

    private final Stack<Path> backStack = new Stack<>();

    public TagEditorView(ITagEditorV2MAdapter modelIn){
        initGUI();
        model = modelIn;
    }

    /**
     * Retrieves the initial file and directory lists and displays them.
     */
    public void start(){
        files = new MultiFilePanel(this);
        directories = new MultiDirectoryPanel(this);
        trackNavigation();

        JSplitPane splitPane  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, directories, files);

        JScrollPane scrollPane = new JScrollPane(splitPane);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Initializes the overall UI.
     * Directory and File Panels will be added on start.
     */
    private void initGUI(){
        setSize(1000,750);
        setTitle("Tag Editor");
        setLayout(new BorderLayout());
        JPanel nav = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> goBack());
        nav.add(backButton, BorderLayout.WEST);
        nav.add(currentName, BorderLayout.EAST);
        add(nav, BorderLayout.NORTH);
    }

    /**
     * Sets the list of files being displayed to the user (i.e. those in the current directory).
     * @param fileList The list of files to display
     */
    public void setFileView(ArrayList<Path> fileList){
        if(directories != null) trackNavigation();
        files.setFiles(fileList);
    }


    public ArrayList<String> getTags(Path file) {
        return model.getTags(file);
    }

    public void setTags(Path file, String tags){
        model.setTags(file, tags);
    }

    /**
     * Tracks the navigation that a user takes in order to do the correct thing when they want to go back
     */
    private void trackNavigation(){
        Path currentDir = directories.getCurrentDirectory();
        currentName.setText(currentDir.toString()+"     ");
        backStack.push(currentDir);
        System.out.println("backStack = " + backStack.toString());
    }

    /**
     * Allows a user to go back. This is nice because they don't have to restart every time they want to go a
     * higher level directory.
     */
    private void goBack(){
        //if only the root directory is in the back stack, do nothing
        if(!(backStack.size() <= 1)){
            //pop the current directory off the stack
            backStack.pop();

            //pop the previous directory off and go to it. It will be re-added by trackNavigation thereafter.
            directories.changeDirectory(backStack.pop());
        }
    }
}
