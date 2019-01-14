package b2w.com.alodjinha.Model;

public class Product {

    private String id;
    private String name;
    private String urlImage;
    private String description;
    private String originalPrice;
    private String newPrice;
    private Category category;

    public Product() {
    }

    public Product(String id, String name, String urlImage, String description, String originalPrice, String newPrice, Category category) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
        this.description = description;
        this.originalPrice = originalPrice;
        this.newPrice = newPrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


