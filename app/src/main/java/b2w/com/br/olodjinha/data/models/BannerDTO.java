package b2w.com.br.olodjinha.data.models;

import com.google.gson.annotations.SerializedName;

public class BannerDTO {

    @SerializedName("id")
    Integer mId;

    @SerializedName("urlImagem")
    String mUrl;

    @SerializedName("linkUrl")
    String mLink;

    public Integer getId() {
        return mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getLink() {
        return mLink;
    }
}
