package com.csquanta.streamline.Models;

import java.util.TreeSet;

public class EvilMonsters {
    public static EvilMonsters evilMonstersStaticObject = new EvilMonsters();
    private String name;
    private String monsterImagePath;
    private int health;
    private int damagePerAttack;
    private int prizePoints;
    private double remainingHealth;

    public double getRemainingHealth() {
        return remainingHealth;
    }

    public void setRemainingHealth(double remainingHealth) {
        this.remainingHealth = remainingHealth;
    }

    public EvilMonsters() {
        firstInitializeEvilMonsters();
    }

    public EvilMonsters(String name, int health, int damagePerAttack, int prizePoints, String monsterImagePath) {
        this.name = name;
        this.health = health;
        this.damagePerAttack = damagePerAttack;
        this.prizePoints = prizePoints;
        this.monsterImagePath = monsterImagePath;
    }

    public String getMonsterImagePath() {
        return monsterImagePath;
    }

    public void setMonsterImagePath(String monsterImagePath) {
        this.monsterImagePath = monsterImagePath;
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
        evilMonstersList.add(new EvilMonsters("Amber", 3000, 25, 500, "/Images/bosses/quest_amber_high.png" ));
        evilMonstersList.add(new EvilMonsters("Armadillo", 1000, 5, 100, "/Images/bosses/quest_armadillo_low.png" ));
        evilMonstersList.add(new EvilMonsters("Axolotl", 1000, 5, 150, "/Images/bosses/quest_axolotl_low.png" ));
        evilMonstersList.add(new EvilMonsters("Badger", 2000, 15, 300, "/Images/bosses/quest_badger_medium.png" ));
        evilMonstersList.add(new EvilMonsters("Butterfly", 1000, 5, 150, "/Images/bosses/quest_butterfly_low.png"));
        evilMonstersList.add(new EvilMonsters("Cheetah", 3000, 25, 500, "/Images/bosses/quest_cheetah_high.png"));
        evilMonstersList.add(new EvilMonsters("Derby", 3000, 25, 500, "/Images/bosses/quest_dilatory_derby_high.png"));
        evilMonstersList.add(new EvilMonsters("Dilatory", 3000, 25, 500, "/Images/bosses/quest_dilatory_high.png"));
        evilMonstersList.add(new EvilMonsters("Dilatory Distress", 2000, 15, 300, "/Images/bosses/quest_dilatoryDistress3_medium.png"));
    }

//    public void setMonsterData(Item item){
//        monsterImagePath.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
//        bgTitle.setText(item.getTitle());
//        bgPriceTag.setText(item.getPrice());
//    }
}
