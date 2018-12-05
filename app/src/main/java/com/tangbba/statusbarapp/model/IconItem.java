package com.tangbba.statusbarapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IconItem implements Parcelable {

    private int mIconResId;
    private String mIconName;

    public IconItem(int iconResId, String iconName) {
        mIconResId = iconResId;
        mIconName = iconName;
    }

    public int getIconResId() {
        return mIconResId;
    }

    public void setIconResId(int iconResId) {
        mIconResId = iconResId;
    }

    public String getIconName() {
        return mIconName;
    }

    public void setIconName(String iconName) {
        mIconName = iconName;
    }

    public IconItem clone() {
        return new IconItem(getIconResId(), getIconName());
    }

    @Override
    public String toString() {
        return "IconItem{" +
                "mIconName='" + mIconName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mIconResId);
        dest.writeString(this.mIconName);
    }

    protected IconItem(Parcel in) {
        this.mIconResId = in.readInt();
        this.mIconName = in.readString();
    }

    public static final Parcelable.Creator<IconItem> CREATOR = new Parcelable.Creator<IconItem>() {
        @Override
        public IconItem createFromParcel(Parcel source) {
            return new IconItem(source);
        }

        @Override
        public IconItem[] newArray(int size) {
            return new IconItem[size];
        }
    };
}
