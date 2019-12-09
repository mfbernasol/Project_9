import java.util.ArrayList;

public class TvShow {

    private String name;
    private String language;
    private String status;
    private String summary;
    private ArrayList<String> genres;


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
    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
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

    public String getSummary() {
        return summary;
    }

    public ArrayList<String> getGenres(){
        return genres;
    }

    public TvShow (String name, String language,ArrayList<String> genres, String status, String summary) {
        this.name = name;
        this.language = language;
        this.genres = genres;
        this.status = status;
        this.summary = summary;
    }

    @Override
    public String toString() {

        String description =
                "Name: " + name + "\n" +
                        "\nLanguage: " + language + "\n" + "\n Genre: " + genres +
                        "\nStatus: " + status + "\n" +
                        "\nSummary: " + summary + "\n";

        return description;
    }




}
