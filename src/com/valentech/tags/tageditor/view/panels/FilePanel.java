package com.valentech.tags.tageditor.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.util.StringJoiner;

/**
 * Created by JD on 9/18/2016.
 */
public class FilePanel extends JPanel {

    public FilePanel(Path file, MultiFilePanel parent){
        setBackground(Color.CYAN);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("file "+file+" clicked");
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

        JLabel myLabel = new JLabel(file.getFileName().toString());
        myLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(myLabel);

        JTextField tagField = new JTextField(20);
        add(tagField);

        StringJoiner sj = new StringJoiner(",");
        parent.getTags(file).forEach(sj::add);
        tagField.setText(sj.toString());

        setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
        setMinimumSize(this.getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
