import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        area.setBounds(15,35,520,600);
        area.setFont(new Font("Arial", Font.PLAIN, 12));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        //button-search
        JButton b=new JButton("Fetch show info");
        b.setBounds(330,10,145,20);

        //button-save to text
        JButton saveToTextBtn = new JButton("Save to text");
        saveToTextBtn.setBounds(40,640,200,20);

        JButton saveToJSONBtn = new JButton("Save to JSON");
        saveToJSONBtn.setBounds(350,640,200,20);

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

                   ArrayList<String> genres = (JSONArray)jObj.get("genres");

                   String language = (String) jObj.get("language");

                   String status = (String) jObj.get("status");

                   String summary = (String) jObj.get("summary");
                   summary = summary.replace(".","."+ "\n").replaceAll("\\<.*?>","");// removes html tags

                   String info = "Show Name: " + showName + "\n"+
                                "Genres: " + genres + "\n" +
                                "\nStatus: " + status + "\n" +
                                "\n Language: " + language + "\n" +
                                "\nDescription: " + "\n" + summary;

                   //shows info to JtextArea
                   area.append(info);


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
