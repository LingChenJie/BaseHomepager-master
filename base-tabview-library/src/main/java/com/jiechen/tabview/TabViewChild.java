package com.jiechen.tabview;

import android.support.v4.app.Fragment;

/**
 * TabViewChild
 * Created by JieChen on 2017/4/1.
 */

public class TabViewChild {

    private int selectIcon;
    private int noSelectIcon;
    private String text;
    private Fragment fragment;

    public TabViewChild() {
    }

    public TabViewChild(int selectIcon, int noSelectIcon, String text, Fragment fragment) {
        this.selectIcon = selectIcon;
        this.noSelectIcon = noSelectIcon;
        this.text = text;
        this.fragment = fragment;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public int getNoSelectIcon() {
        return noSelectIcon;
    }

    public String getText() {
        return text;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public void setNoSelectIcon(int noSelectIcon) {
        this.noSelectIcon = noSelectIcon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
