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
    	 ArrayList<TvShow> show = new ArrayList<TvShow>();
        //initialize text area
        JTextArea area;

        //Textfield
        JFrame f =new JFrame("TV Show Database");
        final JTextField tf=new JTextField();
        tf.setBounds(120,10, 200,20);

        //text area
        area = new JTextArea();
        area.setBounds(15,35,520,600);
        area.setFont(new Font("Arial", Font.PLAIN, 12));
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
        
        
        
        
        //button-save to text
        JButton saveToTextBtn = new JButton("Save to text");
        saveToTextBtn.setBounds(40,640,200,20);
        saveToTextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser(new File("c:\\desktop"));
					if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						if(ButtonFileSave.writeToText(jfc.getSelectedFile(), show)) {
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
						if(ButtonFileSave.writeToJson(jfc.getSelectedFile(), show)) {
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

        //label
        JLabel label = new JLabel("Enter a TV Show");
        label.setBounds(10,5, 100,30);


        //retrieves and parses data from api when button pressed
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

               String str1 = tf.getText();
               String urlString = "http://api.tvmaze.com/singlesearch/shows?q= " + str1;

                JSONParser parser = new JSONParser();
               try {
                   URL url = new URL(urlString);
                   BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                   JSONObject jObj = (JSONObject)parser.parse(reader);

                   String showName  = (String) jObj.get("name");

                   String language = (String) jObj.get("language");

                   String status = (String) jObj.get("status");

                   String summary = (String) jObj.get("summary");
                   summary = summary.replace(".","."+ "\n").replaceAll("\\<.*?>","");// removes html tags

                  /* String info = "Show Name: " + showName + "\n"+
                                "Genres: " + genres + "\n" +
                                "\nStatus: " + status + "\n" +
                                "\n Language: " + language + "\n" +
                                "\nDescription: " + "\n" + summary;
*/
                   //shows info to JtextArea
                   
                  
           
                 //  TvShow tvShow = new TvShow(showName, language, summary);
                  // area.append(tvShow.toString());
                   
                 //  ArrayList<TvShow> show = new ArrayList<TvShow>();
                
                   show.add(new TvShow(showName, language, status, summary));
                   area.setText(DisplayShows.printShowsToScreen(show));

                   reader.close();
               }catch(Exception ex){
                   ex.printStackTrace();
               }



            }
        });


        //adds text area
        f.add(area);

        //adds textfield
        f.add(b);

        //adds buttons
        f.add(saveToTextBtn);
        f.add(saveToJSONBtn);

        f.add(label);
        f.add(tf);

        //width and height for Frame
        f.setSize(600,750);
        f.setLayout(null);
        f.setVisible(true);
    }
}
