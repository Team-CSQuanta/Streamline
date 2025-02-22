package com.csquanta.streamline.Models;

import java.io.Serializable;
import java.util.Comparator;

public class ItemComparator implements Comparator<Item>, Serializable{
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
