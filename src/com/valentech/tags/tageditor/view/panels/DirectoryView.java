package com.valentech.tags.tageditor.view.panels;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Predicate;

import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isRegularFile;

/**
 * Will display directories
 */
public class DirectoryView extends JPanel {
    private Path currentDirectory;
    private ArrayList<Path> dirs = new ArrayList<>();
    private ArrayList<Path> files = new ArrayList<>();

    /**
     * When the directory view is constructed, it builds the initial file and directory path lists
     * using the home directory.
     */
    public DirectoryView(){
        currentDirectory = Paths.get(System.getProperty("user.home"));
        mapCurrentDirectory();
    }


    /**
     * Maps the current directory to either files or directories, doing the proper exclusions
     */
    private void mapCurrentDirectory(){
        dirs = new ArrayList<>();
        files = new ArrayList<>();

        //these predicates indicate that we don't want to see files that are hidden or start with dots
        Predicate<Path> hiddenFile = file -> {
            try{
                //we have to use the dos:hidden property bc windows machines don't consider directories hidden even
                //if we use dos:hidden on them.
                boolean dosHidden = (Boolean) Files.getAttribute(file, "dos:hidden");
                return !dosHidden;
            }
            catch (IOException e){
                //filter out the file if it threw an error
                return false;
            }
        };

        Predicate<Path> dotFile = file -> !file.getFileName().toString().startsWith(".");

        //walk the directory and 'map' it
        try {
            Files.walk(currentDirectory, 1).filter(hiddenFile.and(dotFile)).forEach(filePath -> {

                if(isDirectory(filePath)){
                    dirs.add(filePath);
                } else if(isRegularFile(filePath)){
                    files.add(filePath);
                }
            });
        } catch (IOException e) {
            System.out.println("Unable to iterate over files in the directory.");
            e.printStackTrace();
        }

        System.out.println("dirs = " + dirs);
        System.out.println("files = " + files);
    }
}
