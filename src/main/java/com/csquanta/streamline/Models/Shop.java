package com.csquanta.streamline.Models;

import java.io.Serializable;
import java.util.TreeSet;

public class Shop implements Serializable {
    private final TreeSet<Item> armorsList = new TreeSet<>(new ItemComparator());
    public TreeSet<Item> getArmorsList() {
        return armorsList;
    }
    public void addArmors(Item item){
        armorsList.add(item);
    }
    public void removeArmor(Item item){
        armorsList.remove(item);
    }
    public TreeSet<Item> firstInitializeArmor(){
        // adding healer armor
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_1.png", "Healer one", "100"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_2.png", "Healer two", "200"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_3.png", "Healer three", "300"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_4.png", "Healer four", "400"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_5.png", "Healer five", "500"));
        // adding Rogue armor
        armorsList.add(new Item("/Images/gear/armor/Rogue/rogue_1.png", "Rogue One", "100"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_2.png", "Rogue two", "200"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_3.png", "Rogue three", "300"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_4.png", "Rogue four", "400"));
        armorsList.add(new Item("/Images/gear/armor/Healer/healer_5.png", "Rogue five", "500"));
        // Adding Specials
        armorsList.add(new Item("/Images/gear/armor/Specials/bardRobes.png", "Bard Robes", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/dandySuit.png", "Dandy Suit", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/finnedOceanicArmor.png", "Oceanic Armor", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/lunarWarriorArmor.png", "Lunar Warrior", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/mammothRiderArmor.png", "Mammoth Rider Armor", "600"));


        armorsList.add(new Item("/Images/gear/armor/Specials/nomadsCuirass.png", "Nomads Cuirass", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/pyromancersRobes.png", "Pyromancers Robes", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/roguishRainbowMessengerRobes.png", "Roguish Rainbow Robes", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/samuraiArmor.png", "Samurai Armor", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/sneakthiefRobes.png", "Sneak thief Robes", "600"));
        armorsList.add(new Item("/Images/gear/armor/Specials/snowSovereignRobes.png", "Snow Sovereign Robes", "600"));
        return armorsList;
    }
    private final TreeSet<Item> headWearList = new TreeSet<>(new ItemComparator());

    public TreeSet<Item> getHeadWearList() {
        return headWearList;
    }
    public TreeSet<Item> firstInitializeHeadWear(){
        headWearList.add(new Item("/Images/gear/head/head_healer_1.png", "Head Healer One", "100"));
        headWearList.add(new Item("/Images/gear/head/head_healer_2.png", "Head Healer Two", "200"));
        headWearList.add(new Item("/Images/gear/head/head_healer_3.png", "Head Healer Three", "300"));
        headWearList.add(new Item("/Images/gear/head/head_healer_4.png", "Head Healer Four", "400"));
        headWearList.add(new Item("/Images/gear/head/head_healer_5.png", "Head Healer Five", "500"));
        headWearList.add(new Item("/Images/gear/head/head_special_2.png", "Head Special Two", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_bardHat.png", "Special Bard Hat", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_clandestineCowl.png", "Clandestine Cowl", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_dandyHat.png", "Dandy Hat", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_fireCoralCirclet.png", "Fire Coral Circlet", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_kabuto.png", "Special Kabuto", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_lunarWarriorHelm.png", "Lunar Warrior Helm", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_mammothRiderHelm.png", "Mammoth Rider Helm", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_pyromancersTurban.png", "Pyromancers Turban", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_roguishRainbowMessengerHood.png", "Roguish Rainbow Hood", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_snowSovereignCrown.png", "Sovereign Crown", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_spikedHelm.png", "Spiked Helm", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_turkeyHelmBase.png", "Turkey Helm Base", "600"));
        headWearList.add(new Item("/Images/gear/head/head_special_turkeyHelmGilded.png", "Turkey Helm Gilded", "600"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_1.png", "Head Warrior One", "100"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_2.png", "Head Warrior Two", "200"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_3.png", "Head Warrior Three", "300"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_4.png", "Head Warrior Four", "400"));
        headWearList.add(new Item("/Images/gear/head/head_warrior_5.png", "Head Warrior Five", "500"));
        return headWearList;
    }

}
