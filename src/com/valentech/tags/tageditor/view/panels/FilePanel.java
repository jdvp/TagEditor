package com.valentech.tags.tageditor.view.panels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.nio.file.Path;
import java.util.StringJoiner;

/**
 * Created by JD on 9/18/2016.
 */
public class FilePanel extends JPanel {

    public FilePanel(Path file, MultiFilePanel parent){
        setBackground(Color.CYAN);

        JLabel myLabel = new JLabel(file.getFileName().toString());
        myLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(myLabel);

        JTextField tagField = new JTextField(20);
        add(tagField);

        refreshData(file, parent, tagField);

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
                System.out.println("addSaveButton");
                JButton save = new JButton("Save Changes");
                save.addActionListener(e -> {
                    parent.setTags(file, tagField.getText());
                    alreadyEditing = false;
                    refreshData(file, parent, tagField);
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
