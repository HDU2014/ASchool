package com.hdu.tx.aschool.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/4.
 */
public class InterestTabsEntitiy implements Serializable{
    private String tab;
    private boolean isSelected;

    public InterestTabsEntitiy(String tab) {
        this.tab=tab;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
