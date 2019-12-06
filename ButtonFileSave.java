import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ButtonFileSave {
	public static boolean writeToText(File f, ArrayList<TvShow> shows) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for(TvShow t: shows) {
				pw.println(t);
			}
			pw.close();
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	public static boolean writeToJson(File f, ArrayList<TvShow> shows) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			JSONArray array = new JSONArray();
			JSONObject memObj;
			for(TvShow t: shows) {
				memObj = new JSONObject();
				memObj.put("Name", t.getName());
				memObj.put("Language", t.getLanguage());
				memObj.put("Status", t.getStatus());
				memObj.put("Summary", t.getSummary());
				array.add(memObj);
			}
			JSONObject outer = new JSONObject();
			outer.put("TV Shows", array);
			pw.println(outer.toJSONString());
			pw.close();
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
}
