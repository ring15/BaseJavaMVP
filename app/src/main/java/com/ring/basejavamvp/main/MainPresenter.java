package com.ring.basejavamvp.main;

public class MainPresenter extends MainContract.MyPresenter{
    private MainModel mainModel;

    public MainPresenter() {
        mainModel = new MainModel();
    }
}
