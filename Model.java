public class Model {
	private String name;
	private String language;
	private String summary;
	
	public String getName() {
		return name;
	}
	public String getLanguage() {
		return language;
	}
	public String getSummary() {
		return summary;
	}
	
	public Model (String name, String language, String summary) {
		this.name = name;
		this.language = language;
		this.summary = summary;
	}
	
	public String toString() {
		String description = "Name: " + name + "\nLanguage: " + language + "\nSummary: " + summary + "\n";
		return description;
	}
		

	

}
