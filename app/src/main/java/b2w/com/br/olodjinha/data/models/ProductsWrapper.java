package b2w.com.br.olodjinha.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductsWrapper implements Serializable {

    @SerializedName("data")
    List<ProductDTO> mData;

    public List<ProductDTO> getData() {
        return mData;
    }
}
