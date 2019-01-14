package b2w.com.br.olodjinha.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryWrapper implements Serializable {

    @SerializedName("data")
    List<CategoryDTO> mData;

    public List<CategoryDTO> getData() {
        return mData;
    }

    public void setData(List<CategoryDTO> data) {
        mData = data;
    }
}
