package b2w.com.br.olodjinha.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BannerWrapper implements Serializable{

    @SerializedName("data")
    List<BannerDTO> mData;

    public List<BannerDTO> getData() {
        return mData;
    }
}
