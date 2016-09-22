package com.valentech.tags.tageditor.view.panels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.nio.file.Path;
import java.util.StringJoiner;

/**
 * Displays a single file element.
 * This consists of the file's name and a list of its tags.
 * If the tags have been edited, it also includes a save button.
 */
class FilePanel extends JPanel {

    public FilePanel(Path file, MultiFilePanel parent){
        setBackground(Color.CYAN);

        JLabel myLabel = new JLabel(file.getFileName().toString());
        myLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(myLabel);

        JTextField tagField = new JTextField(20);
        add(tagField);

        refreshData(file, parent, tagField);

        /*
        If the tag field is edited, display a 'save' button to the user.
        We do this instead of saving automatically because we aren't sure if the user even wants to save and it
        allows for less writes to the disk.
        */
        tagField.getDocument().addDocumentListener(new DocumentListener() {

            private boolean alreadyEditing = false;
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!alreadyEditing) addSaveButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!alreadyEditing) addSaveButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!alreadyEditing) addSaveButton();
            }

            private void addSaveButton(){
                alreadyEditing = true;
                JButton save = new JButton("Save Changes");
                save.addActionListener(e -> {
                    parent.setTags(file, tagField.getText());
                    refreshData(file, parent, tagField);
                    alreadyEditing = false;
                    FilePanel.this.remove(save);
                    FilePanel.this.refreshSize();
                });
                FilePanel.this.add(save);
                FilePanel.this.refreshSize();
                save.validate();
            }

        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        refreshSize();
    }

    /**
     * Re-sizes this panel based on the content within
     */
    private void refreshSize(){
        setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
        setMinimumSize(this.getPreferredSize());
        revalidate();
        repaint();
    }

    private void refreshData(Path file, MultiFilePanel parent, JTextField tagField){
        StringJoiner sj = new StringJoiner(",");
        parent.getTags(file).forEach(sj::add);
        tagField.setText(sj.toString());
    }


}
