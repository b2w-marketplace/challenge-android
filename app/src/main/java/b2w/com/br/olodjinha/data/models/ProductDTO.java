package b2w.com.br.olodjinha.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {

    @SerializedName("id")
    Integer mId;

    @SerializedName("descricao")
    String mDescription;

    @SerializedName("urlImagem")
    String mUrl;

    @SerializedName("precoPor")
    BigDecimal mPrice;

    @SerializedName("precoDe")
    BigDecimal mPriceWithDescount;

    @SerializedName("nome")
    String mName;

    @SerializedName("categoria")
    CategoryDTO mCategoryDTO;

    public Integer getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public BigDecimal getPrice() {
        return mPrice;
    }

    public BigDecimal getPriceWithDiscount() {
        return mPriceWithDescount;
    }

    public String getName() {
        return mName;
    }

    public CategoryDTO getCategoryDTO() {
        return mCategoryDTO;
    }

    public void setId(int id) {
        mId = id;
    }
}
