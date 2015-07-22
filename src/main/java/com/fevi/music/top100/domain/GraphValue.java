package com.fevi.music.top100.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minyumi on 15. 7. 23..
 */
public class GraphValue {
    private String name;
    private List<Integer> data;

    public GraphValue() {
        this.data = new ArrayList<>();
    }

    public GraphValue(String name) {
        this.name = name;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GraphValue{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
