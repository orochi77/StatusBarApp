package com.tangbba.statusbarapp.model;

import java.util.ArrayList;
import java.util.List;

public class HomeIcons {

    private List<IconItem> mIconItems;

    public HomeIcons(List<IconItem> iconItems) {
        mIconItems = iconItems;
    }

    public List<IconItem> getIconItems() {
        return mIconItems;
    }

    public void setIconItems(List<IconItem> iconItems) {
        mIconItems = iconItems;
    }

    public List<IconItem> clone() {
        List<IconItem> iconItems = new ArrayList<>();
        for (IconItem icon : mIconItems) {
            iconItems.add(icon.clone());
        }
        return iconItems;
    }
}
