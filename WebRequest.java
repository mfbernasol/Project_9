import java.net.*;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/* To test API request and parse infomation */
public class WebRequest {


    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter show: ");
            String search = " ";
            search = sc.nextLine();

            String urlString = "http://api.tvmaze.com/singlesearch/shows?q= " + search; //API request
            URL url = new URL(urlString);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); //reads url
            String line;

            JSONObject jsonObj = (JSONObject) parser.parse(reader); //p


            String showName = (String) jsonObj.get("name");
            System.out.println("Show Name: " + showName);

            ArrayList<String> list = new ArrayList<String>();
            JSONArray genres = (JSONArray) jsonObj.get("genres");
//            System.out.println("Genre(s): " + genres);
            Iterator<String> iterator = genres.iterator();
            while(iterator.hasNext()) {
                System.out.println("Genre: " + iterator.next());
            }

            String language = (String) jsonObj.get("language");
            System.out.println("Language: " + language);

            String summary = (String) jsonObj.get("summary");
            summary = summary.replace(".","."+ "\n").replaceAll("\\<.*?>","");// removes html tags
            System.out.println("Show Summary: " + summary);


//            while ((line = reader.readLine()) != null) {
//
//                System.out.println(line);
//            }

            // close our reader
            reader.close();
        }catch (Exception ex){
            System.out.println("Show not found");
            ex.printStackTrace();
        }
    }

}