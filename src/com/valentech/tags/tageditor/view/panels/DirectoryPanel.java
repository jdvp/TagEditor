package com.valentech.tags.tageditor.view.panels;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;

/**
 * Panel used to show a single directory entry.
 * Generally I wouldn't couple this so closely to its parent (DirectoryView) but I didn't make this class to enable
 * decoupling but more to make the code easier to read.
 */
public class DirectoryPanel extends JPanel{

    public DirectoryPanel(Path directoryName, DirectoryView parent){
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        //Add a listener to this directory that will tell the parent to switch the directory we are in
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("directory "+directoryName+" clicked");
                parent.changeDirectory(directoryName);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        JLabel myLabel = new JLabel(directoryName.getFileName().toString());
        myLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(myLabel);


        setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
    }
}
