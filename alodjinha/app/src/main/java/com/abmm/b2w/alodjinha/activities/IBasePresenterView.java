package com.abmm.b2w.alodjinha.activities;

public interface IBasePresenterView {

    void showError();
    void showError(int code);
    void releaseUi();

}
