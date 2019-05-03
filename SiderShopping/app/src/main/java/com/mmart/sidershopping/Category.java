package com.mmart.sidershopping;

public class Category {

    private int mId;
    private String mNome;
    private String mImage;

    public Category(int mId, String mNome, String image) {
        this.mId = mId;
        this.mNome = mNome;
        this.mImage = image;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNome() {
        return mNome;
    }

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
