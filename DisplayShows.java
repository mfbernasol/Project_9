import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.security.cert.CertPathValidatorException;
import java.util.*;
import java.util.ArrayList;

import javax.swing.JTextArea;

//controller
public class DisplayShows {

    public static ArrayList<TvShow> requestShowData(String show) {
        ArrayList<TvShow> shows = new ArrayList<>();
        String urlString = "http://api.tvmaze.com/singlesearch/shows?q= " + show;
        JSONParser parser = new JSONParser();
        try {

            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            JSONObject jObj = (JSONObject) parser.parse(reader);

            String showName = (String) jObj.get("name");

            //  ArrayList<String> genres = (JSONArray)jObj.get("genres");

            String language = (String) jObj.get("language");

            String status = (String) jObj.get("status");

            String summary = (String) jObj.get("summary");
            summary = summary.replace(".", "." + "\n").replaceAll("\\<.*?>", "");// removes html tags
            shows.add(new TvShow(showName, language, status, summary));


            reader.close();
            return shows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }



    public static String printShowsToScreen(ArrayList<TvShow> tvShows){
        String text = "";
        for(TvShow t: tvShows){
            text += t + "\n";
        }
        return text;


    }

}
