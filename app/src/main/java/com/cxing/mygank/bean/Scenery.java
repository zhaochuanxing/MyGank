package com.cxing.mygank.bean;

/**
 * Created by zhao on 17-8-6.
 */

public class Scenery {

    private String name;
    private int imgId;

    public Scenery(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
