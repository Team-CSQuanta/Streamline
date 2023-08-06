package com.csquanta.streamline.Models;

import java.util.Comparator;

public class ArmorComp implements Comparator<Armor> {
    @Override
    public int compare(Armor o1, Armor o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
