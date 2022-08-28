package com.bjpowernode.hometalk.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class News  implements Serializable {

    private ArrayList<String> arrayList;

    public News(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public News() {
    }
    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "News{" +
                "arrayList=" + arrayList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(getArrayList(), news.getArrayList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArrayList());
    }
}