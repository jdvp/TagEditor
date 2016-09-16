package com.valentech.tags.tageitor;

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
    private UserDefinedFileAttributeView view;

    public TagEditor(String filename){
        Path path = Paths.get(filename);
        view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
    }

    public void setTags(){
        String tags="[]";
        try{
            view.write(TAG_FIELD_NAME, Charset.defaultCharset().encode(tags));
        } catch(IOException e){
            System.out.println("Unable to set tags for file");
        }
    }

    public ArrayList<String> getTags(){
        try{
            ByteBuffer buf = ByteBuffer.allocate(view.size(TAG_FIELD_NAME));
            view.read(TAG_FIELD_NAME, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println("value "+value);
        } catch (IOException e){
            System.out.println("The file has no tags.");
        }
        return null;
    }

    public void addTag(){

    }
}
