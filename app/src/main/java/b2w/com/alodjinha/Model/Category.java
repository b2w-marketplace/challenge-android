package b2w.com.alodjinha.Model;

public class Category {

    private String id;
    private String description;
    private String urlImage;


    public Category() {
    }

    public Category(String id, String urlImage, String description) {
        this.id = id;
        this.description = description;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
