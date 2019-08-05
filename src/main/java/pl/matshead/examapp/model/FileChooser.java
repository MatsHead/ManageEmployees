package pl.matshead.examapp.model;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is for choosing file when we want export to xml file
 * It enforcing java window to open and choose file
 */

public class FileChooser {

    public String chooseFile(){
        System.out.println("***************** innvoked");
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XLS", "xls");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());
            return chooser.getSelectedFile().getAbsolutePath();

        } else {
            return "";
        }
    }
}
