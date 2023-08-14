package com.csquanta.streamline.Models;

import javafx.scene.image.Image;

import java.io.File;
import java.util.EventListener;
import java.util.Objects;
import java.util.TreeSet;

public class EvilMonsters {
    private String name;
    private String monsterImagePath;
    private int health;
    private int damagePerAttack;
    private int prizePoints;

    public EvilMonsters(String name, int health, int damagePerAttack, int prizePoints) {
        this.name = name;
        this.health = health;
        this.damagePerAttack = damagePerAttack;
        this.prizePoints = prizePoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamagePerAttack() {
        return damagePerAttack;
    }

    public void setDamagePerAttack(int damagePerAttack) {
        this.damagePerAttack = damagePerAttack;
    }

    public int getPrizePoints() {
        return prizePoints;
    }

    public void setPrizePoints(int prizePoints) {
        this.prizePoints = prizePoints;
    }

    public TreeSet<EvilMonsters> getEvilMonstersList() {
        return evilMonstersList;
    }

    public void setEvilMonstersList(TreeSet<EvilMonsters> evilMonstersList) {
        this.evilMonstersList = evilMonstersList;
    }

    private TreeSet<EvilMonsters> evilMonstersList = new TreeSet<>(new EvilMonstersComparator());
    public void firstInitializeEvilMonsters(){
//        evilMonstersList.add(new EvilMonsters());
    }
//    public void setMonsterData(Item item){
//        monsterImagePath.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
//        bgTitle.setText(item.getTitle());
//        bgPriceTag.setText(item.getPrice());
//    }
}
