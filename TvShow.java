import java.util.ArrayList;

public class TvShow {

    private String name;
    private String language;
    private String status;
    private String summary;
    private ArrayList<String> genre;

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }
    public String getLanguage() {
        return language;
    }

    public String getStatus(){
        return status;
    }

    public ArrayList<String> getGenre() {
    	return genre;
    }
    public String getSummary() {
        return summary;
    }

    public TvShow (String name, String language, ArrayList<String> genre, String status, String summary) {
        this.name = name;
        this.language = language;
        this.status = status;
        this.genre = genre;
        this.summary = summary;
    }

@Override
    public String toString() {
        genre.toString().replace("[]","").replace( ""," ");
        String description =
                "Name: " + name + "\n" +
                "\nLanguage: " + language + "\n" + "\n Genre: " + genre +
                "\nStatus: " + status + "\n" +
                "\nSummary: " + summary + "\n";

        return description;
    }




}
