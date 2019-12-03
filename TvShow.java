import java.util.ArrayList;

public class TvShow {

	private String name;
	private String language;
	//private ArrayList<String> genre;
	private String summary;
	
	public String getName() {
		return name;
	}
	public String getLanguage() {
		return language;
	}
	//public ArrayList<String> getGenre() {
	//	return genre;
	//}
	public String getSummary() {
		return summary;
	}
	
	public TvShow (String name, String language, String summary) {
		this.name = name;
		this.language = language;
		//this.genre = genre;
		this.summary = summary;
	}
	
	public String toString() {
		String description = "Name: " + name + "\nLanguage: " + language +  "\nSummary: " + summary + "\n";
		return description;
	}
		

	

}
