package b2w.com.br.olodjinha.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    @SerializedName("id")
    Integer mId;

    @SerializedName("descricao")
    String mDescription;

    @SerializedName("urlImagem")
    String mUrl;

    public Integer getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
