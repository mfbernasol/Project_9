import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

public class View {


    public static void main(String[] args) {
        //initialize text area
        JTextArea area;

        //Textfield
        JFrame f =new JFrame("TV Show Database");
        final JTextField tf=new JTextField();
        tf.setBounds(120,10, 200,20);

        //text area
        area = new JTextArea();
        area.setBounds(15,35,600,600);
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        //button-search
        JButton b=new JButton("Fetch show info");
        b.setBounds(330,10,145,20);




        //menu bar
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenu mnuHelp = new JMenu("Help");
        JMenuItem miExit = new JMenuItem("Exit");
        JMenuItem miAbout = new JMenuItem("About");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miExit);
        mbar.add(mnuFile);
        f.setJMenuBar(mbar);

        miAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Enter in a popular tv show you want to learn about! Click on Fetch Show Info button to "
                        + "fetch the tv show \ninformation. Then click on one of the save buttons to be able to save in either Text or JSON file. "
                        + "Happy \nfetching!");
            }
        });;
        mnuHelp.add(miAbout);
        mbar.add(mnuHelp);
        f.setJMenuBar(mbar);


        //label
        JLabel label = new JLabel("Enter a TV Show");
        label.setBounds(10,5, 100,30);




        final ArrayList<TvShow>[] show = new ArrayList[]{new ArrayList<>()};
        //retrieves and parses data from api when button pressed
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String str1 = tf.getText();
                show[0] = DisplayShows.requestShowData(str1);
                area.append(DisplayShows.printShowsToScreen(show[0]));

            }
        });


        //button to enter clear search
        JButton clearSearch = new JButton("Start a new search");
        clearSearch.setBounds(480,10,145,20);
        clearSearch.setFont(new Font("Dialog",Font.BOLD,12));
        clearSearch.setHorizontalAlignment(SwingConstants.LEFT);

        clearSearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf.setText("");
                area.setText("");
            }
        });

        //button-save to text
        JButton saveToTextBtn = new JButton("Save to text");
        saveToTextBtn.setBounds(40,640,200,20);
        saveToTextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser jfc = new JFileChooser(new File("c:\\desktop"));
                    if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        if(ButtonFileSave.writeToText(jfc.getSelectedFile(), show[0])) {
                            JOptionPane.showMessageDialog(null, "Tv Shows Saved!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Tv Shows could not be saved.");
                        }
                    }
                } catch(Exception ex) {
                    System.out.println("Could not save the file");
                }
            }
        });
        //save to json button
        JButton saveToJSONBtn = new JButton("Save to JSON");
        saveToJSONBtn.setBounds(350,640,200,20);
        saveToJSONBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser jfc = new JFileChooser(new File("c:\\desktop"));
                    if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        if(ButtonFileSave.writeToJson(jfc.getSelectedFile(), show[0])) {
                            JOptionPane.showMessageDialog(null, "Tv Shows Saved!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Tv Shows could not be saved.");
                        }
                    }
                } catch(Exception ex) {
                    System.out.println("Could not save the file");
                }
            }
        });


        //adds text area
        f.add(area);

        //adds textfield
        f.add(b);
        f.add(clearSearch);
        //adds buttons
        f.add(saveToTextBtn);
        f.add(saveToJSONBtn);

        f.add(label);
        f.add(tf);

        //width and height for Frame
        f.setSize(650,750);
        f.setLayout(null);
        f.setVisible(true);
    }
}
