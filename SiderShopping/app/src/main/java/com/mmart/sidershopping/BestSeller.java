package com.mmart.sidershopping;

public class BestSeller {

    private int mId;
    private String mName;
    private String mOldPrice;
    private String mNewPrice;
    private String mImage;

    public BestSeller(int mId, String mName, String mOldPrice, String mNewPrice, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mOldPrice = mOldPrice;
        this.mNewPrice = mNewPrice;
        this.mImage = mImage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmOldPrice() {
        return mOldPrice;
    }

    public void setmOldPrice(String mOldPrice) {
        this.mOldPrice = mOldPrice;
    }

    public String getmNewPrice() {
        return mNewPrice;
    }

    public void setmNewPrice(String mNewPrice) {
        this.mNewPrice = mNewPrice;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

}
