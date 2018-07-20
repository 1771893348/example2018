package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/4/6.
 */
public class TabLayoutcontentBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name; //标题

    public TabLayoutcontentBean() {
    }

    public TabLayoutcontentBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
