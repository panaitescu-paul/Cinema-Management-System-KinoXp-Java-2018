package antelopes.kinoxp.models;

public class Movie {
    private String name, genre;
    private int ageLimit, id;
    private String img_url;

    public Movie(){

    }
    public Movie(int id){
        this.id = id;
    }

    public Movie(int id, String name, String genre, int ageLimit) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.ageLimit = ageLimit;
    }

    public Movie(String name, String genre, int ageLimit) {
        this.name = name;
        this.genre = genre;
        this.ageLimit = ageLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg_url(String img_url){
        this.img_url = img_url;
    }

    public String getImg_url(){
        return img_url;
    }
}

