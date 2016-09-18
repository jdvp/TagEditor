package com.valentech.tags.tageditor.model;

import com.valentech.tags.tageditor.exceptions.MalformedTagStringException;
import com.valentech.tags.tageditor.exceptions.TagTooLongException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;

/**
 * Class that will edit the tags on a specific, given file.
 */
public class TagEditor {

    private static final String TAG_FIELD_NAME = "user.tag";
    private final UserDefinedFileAttributeView view;

    /**
     * Create a new tag editor for a specific file
     * @param filename The name of the file to edit tags for
     */
    public TagEditor(String filename){
        Path path = Paths.get(filename);
        view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
    }

    /**
     * If we already have a Path object use it instead as a constructor
     */
    public TagEditor(Path path){
        view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
    }

    /**
     * Writes to the tags field of the file
     * @param tags the tags to write
     */
    public void setTags(ArrayList<String> tags){
        String tagsAsString = TagParser.unparse(tags);
        try{
            view.write(TAG_FIELD_NAME, Charset.defaultCharset().encode(tagsAsString));
        } catch(IOException e){
            System.out.println("Unable to set tags for file");
        }
    }

    /**
     * Gets the tags on the file
     * @return an ArrayList of the tags found on the file
     */
    public ArrayList<String> getTags(){
        try{
            ByteBuffer buf = ByteBuffer.allocate(view.size(TAG_FIELD_NAME));
            view.read(TAG_FIELD_NAME, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            return TagParser.parse(value);
        } catch (IOException e){
            System.out.println("The file has no tags.");
        } catch (MalformedTagStringException e){
            System.out.println("The tags on the file are corrupted.");
            //delete the corrupted tags since we can't use them anyway
            clearTags();
        }
        return new ArrayList<>();
    }

    /**
     * Convenience method for adding a single tag
     * @param tag the tag to add
     */
    public void addTag(String tag) {
        //we shouldn't end up throwing this, but just in case...
        if(tag.length() > Integer.MAX_VALUE - 2)
            throw new TagTooLongException();

        ArrayList<String> tags = getTags();
        tags.add(tag);
        setTags(tags);
    }

    /**
     * Clears all of the tags on the file
     */
    public void clearTags(){
        setTags(new ArrayList<>());
    }

}
