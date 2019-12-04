import java.util.ArrayList;

import javax.swing.JTextArea;

public class DisplayShows {
    public static String printShowsToScreen(ArrayList<TvShow> tvShows){
        String text = "";
    	for(TvShow t: tvShows){
            text += t + "\n";
        }
    	return text;
    	
    }

}