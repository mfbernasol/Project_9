import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ButtonFileSave {
	public static boolean write(File f, ArrayList<TvShow> shows) {
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
}
