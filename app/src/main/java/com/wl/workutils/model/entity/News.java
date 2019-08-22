package com.wl.workutils.model.entity;

/**
 * create by wyh on 2019/4/1
 */

public class News {

    public static final String IMAGE_NEWS = "img";
    private String type;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
