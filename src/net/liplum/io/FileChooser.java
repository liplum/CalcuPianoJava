package net.liplum.io;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class FileChooser {

    private JFileChooser jFileChooser = new JFileChooser();
    private Container container;

    public FileChooser(Container c, String fileFilter) {
        this.container = c;
        jFileChooser.setFileFilter(new ExtensionFileFilter(fileFilter));
    }

    public void chooseFile() {
        int i = jFileChooser.showOpenDialog(container);// Show dialog for selecting file

        // Check whether the clicked is "open"
        if (i == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
        }
    }


    private static class ExtensionFileFilter extends FileFilter {

        private String regulation;

        private ExtensionFileFilter(String reg) {
            this.regulation = reg;
        }


        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith("." + regulation);
        }

        @Override
        public String getDescription() {
            return regulation;
        }
    }


}