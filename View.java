import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.*;

public class View {

    public static void parseJSON(String url){

    }


    public static void main(String[] args) {

        JTextArea area;


        //Textfield
        JFrame f =new JFrame("Button Example");
        final JTextField tf=new JTextField();
        tf.setBounds(120,10, 200,20);


        area = new JTextArea();
        area.setBounds(40,35,450,400);

        //button-search
        JButton b=new JButton("Fetch show info");
        b.setBounds(330,10,145,20);

        //button-save to text
        JButton saveToTextBtn = new JButton("Save to text");
        saveToTextBtn.setBounds(40,450,200,20);

        JButton saveToJSONBtn = new JButton("Save to JSON");
        saveToJSONBtn.setBounds(280,450,200,20);

        //label
        JLabel label = new JLabel("Enter a TV Show");
        label.setBounds(10,5, 100,30);



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

                   String summary = (String) jObj.get("summary");
                   summary = summary.replace(".","."+ "\n").replaceAll("\\<.*?>","");// removes html tags


                   String info = "Show Name: " + showName +
                           "\n Language: " + language;
                   area.append(info);


                   reader.close();
               }catch(Exception ex){
                   ex.printStackTrace();
               }



            }
        });



        f.add(area);

        f.add(b);
        f.add(saveToTextBtn);
        f.add(saveToJSONBtn);

        f.add(label);
        f.add(tf);
        f.setSize(550,550);
        f.setLayout(null);
        f.setVisible(true);
    }
}