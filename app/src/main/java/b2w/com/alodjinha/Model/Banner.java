package b2w.com.alodjinha.Model;

public class Banner {

    private String id;
    private String urlImage;
    private String linkUrl;

    public Banner() {
    }

    public Banner(String id, String urlImage, String linkUrl) {
        this.id = id;
        this.urlImage = urlImage;
        this.linkUrl = linkUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
