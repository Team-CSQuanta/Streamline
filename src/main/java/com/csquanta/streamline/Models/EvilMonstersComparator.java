package com.csquanta.streamline.Models;

import java.util.Comparator;

public class EvilMonstersComparator implements Comparator<EvilMonsters> {
    @Override
    public int compare(EvilMonsters o1, EvilMonsters o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
