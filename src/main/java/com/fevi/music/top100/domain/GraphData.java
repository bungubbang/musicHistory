package com.fevi.music.top100.domain;

import java.util.List;

/**
 * Created by Minyumi on 15. 7. 23..
 */
public class GraphData {
    List<Integer> keys;
    List<GraphValue> values;

    public GraphData(List<Integer> keys, List<GraphValue> values) {
        this.keys = keys;
        this.values = values;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public void setKeys(List<Integer> keys) {
        this.keys = keys;
    }

    public List<GraphValue> getValues() {
        return values;
    }

    public void setValues(List<GraphValue> values) {
        this.values = values;
    }
}
